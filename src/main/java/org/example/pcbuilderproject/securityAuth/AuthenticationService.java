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
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .username(request.getUsername())
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);

        var accessToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

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

        var sessionDto = new SessionDTO(
                user.getId(),
                accessToken,
                refreshToken,
                "Bearer",
                new Date().toString(), // loginDate
                new Date(System.currentTimeMillis() +1000*60*24).toString(), // expire
                new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000).toString() // refreshExpire
        );



        return AuthenticationResponse.builder()
                .user(userDto)
                .session(sessionDto)
                .build();
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail()).orElseThrow();



        var accessToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

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

        var sessionDto = new SessionDTO(
                user.getId(),
                accessToken,
                refreshToken,
                "Bearer",
                new Date().toString(), // loginDate
                new Date(System.currentTimeMillis() +1000*60*24).toString(), // expire
                new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000).toString() // refreshExpire
        );



        return AuthenticationResponse.builder()
                .user(userDto)
                .session(sessionDto)
                .build();
    }
}
