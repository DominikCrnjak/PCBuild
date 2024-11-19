package org.example.pcbuilderproject.securityAuth;

import lombok.RequiredArgsConstructor;
import org.example.pcbuilderproject.securityConfig.JwtService;
import org.example.pcbuilderproject.securityUser.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /**
     * Registrira novog korisnika.
     * @param request Podaci za registraciju korisnika.
     * @return Odgovor s podacima o autentifikaciji.
     */
    public AuthenticationResponse register(RegisterRequest request) {
        // Kreiraj novog korisnika
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .username(request.getUsername())
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();
        repository.save(user);

        // Generiraj JWT tokene
        var accessToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        // Kreiraj DTO za korisnika
        var userDto = new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getRole().name(),
                true
        );

        // Kreiraj DTO za sesiju
        var sessionDto = new SessionDTO(
                user.getId(),
                accessToken,
                refreshToken,
                "Bearer",
                new Date().toString(), // loginDate
                new Date(System.currentTimeMillis() + 1000 * 60 * 24).toString(), // expire
                new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000).toString() // refreshExpire
        );

        // Vraća odgovor s podacima o autentifikaciji
        return AuthenticationResponse.builder()
                .user(userDto)
                .session(sessionDto)
                .build();
    }

    /**
     * Autentificira korisnika.
     * @param request Podaci za autentifikaciju korisnika.
     * @return Odgovor s podacima o autentifikaciji.
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        // Autentificiraj korisnika
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail()).orElseThrow();

        // Generiraj JWT tokene
        var accessToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        // Kreiraj DTO za korisnika
        var userDto = new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getRole().name(),
                false
        );

        // Kreiraj DTO za sesiju
        var sessionDto = new SessionDTO(
                user.getId(),
                accessToken,
                refreshToken,
                "Bearer",
                new Date().toString(), // loginDate
                new Date(System.currentTimeMillis() + 1000 * 60 * 24).toString(), // expire
                new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000).toString() // refreshExpire
        );

        // Vraća odgovor s podacima o autentifikaciji
        return AuthenticationResponse.builder()
                .user(userDto)
                .session(sessionDto)
                .build();
    }
}