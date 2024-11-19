package org.example.pcbuilderproject.securityController;

import lombok.RequiredArgsConstructor;
import org.example.pcbuilderproject.securityAuth.*;
import org.example.pcbuilderproject.securityConfig.JwtService;
import org.example.pcbuilderproject.securityUser.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthenticationController {
    private final AuthenticationService service;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }
    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) {
        String refreshToken = request.getRefreshToken();

        // Provjera valjanosti refresh tokena
        if (!jwtService.validateToken(refreshToken)) {
            return ResponseEntity.status(403).body("Invalid refresh token");
        }

        // Ekstraktiraj korisničko ime iz refresh tokena
        String username = jwtService.extractUsername(refreshToken);

        var user = userRepository.findByEmail(username).orElseThrow();
        String newAccessToken = jwtService.generateToken(new HashMap<>(), user);

        return ResponseEntity.ok(new AccessTokenResponse(newAccessToken));
    }
}
