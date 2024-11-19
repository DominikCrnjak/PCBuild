package org.example.pcbuilderproject.securityConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtService {

    private static final String SECRET_KEY = "W0jkyDxVmK3nrDlHkM6Spu+haQTqYKH3IpWvDoPDQtIpBAC5bguQIdIO63fOkHMH";

    /**
     * Izvlači korisničko ime iz JWT tokena.
     * @param token JWT token.
     * @return Korisničko ime.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Generira JWT token za korisnika.
     * @param userDetails Detalji korisnika.
     * @return JWT token.
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Generira JWT token s dodatnim claimovima.
     * @param extractClaims Dodatni claimovi.
     * @param userDetails Detalji korisnika.
     * @return JWT token.
     */
    public String generateToken(Map<String, Object> extractClaims, UserDetails userDetails) {
        extractClaims.put("roles", userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));

        return Jwts.builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
    }

    /**
     * Generira refresh token za korisnika.
     * @param userDetails Detalji korisnika.
     * @return Refresh token.
     */
    public String generateRefreshToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Provjerava je li JWT token valjan.
     * @param token JWT token.
     * @param userDetails Detalji korisnika.
     * @return True ako je token valjan, inače false.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * Provjerava je li JWT token istekao.
     * @param token JWT token.
     * @return True ako je token istekao, inače false.
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Izvlači datum isteka iz JWT tokena.
     * @param token JWT token.
     * @return Datum isteka.
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Izvlači sve claimove iz JWT tokena.
     * @param token JWT token.
     * @return Claimovi.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }

    /**
     * Izvlači specificirani claim iz JWT tokena.
     * @param token JWT token.
     * @param claimsResolver Funkcija za rješavanje claimova.
     * @param <T> Tip claima.
     * @return Vrijednost claima.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Izvlači uloge iz JWT tokena.
     * @param token JWT token.
     * @return Lista uloga.
     */
    public List<String> extractRoles(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("roles", List.class);
    }

    /**
     * Dobiva ključ za potpisivanje JWT tokena.
     * @return Ključ za potpisivanje.
     */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Validira JWT token.
     * @param token JWT token.
     * @return True ako je token valjan, inače false.
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
