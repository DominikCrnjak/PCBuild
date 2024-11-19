package org.example.pcbuilderproject.securityConfig;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    /**
     * Filtrira dolazne HTTP zahtjeve i provjerava JWT token.
     * @param request HTTP zahtjev.
     * @param response HTTP odgovor.
     * @param filterChain Lanac filtera.
     * @throws ServletException Ako dođe do greške u servisu.
     * @throws IOException Ako dođe do I/O greške.
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // Provjeri je li Authorization header prisutan i počinje li s "Bearer"
        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Izvuci JWT token iz Authorization headera
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);

        // Provjeri je li korisničko ime prisutno i je li korisnik već autentificiran
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            // Provjeri je li JWT token valjan
            if (jwtService.isTokenValid(jwt, userDetails)) {
                // Izvuci uloge iz JWT tokena
                List<String> roles = jwtService.extractRoles(jwt);
                List<GrantedAuthority> authorities = roles.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

                // Kreiraj autentifikacijski token
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Postavi autentifikacijski token u SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Nastavi s lancem filtera
        filterChain.doFilter(request, response);
    }
}
