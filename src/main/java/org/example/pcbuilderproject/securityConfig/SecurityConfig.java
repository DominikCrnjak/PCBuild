package org.example.pcbuilderproject.securityConfig;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;

    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        // Konfiguriraj sigurnosni lanac filtera
        http
                .csrf(csrf -> csrf.disable()) // Onemogući CSRF zaštitu
                .cors(withDefaults()) // Omogući CORS s zadanim postavkama
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/auth/**").permitAll() // Dozvoli pristup svim zahtjevima na /api/auth/**
                        .anyRequest().authenticated() // Svi ostali zahtjevi moraju biti autentificirani
                )
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Postavi politiku kreiranja sesije na STATELESS
                )
                .authenticationProvider(authenticationProvider) // Postavi pružatelja autentifikacije
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // Dodaj JWT filter prije UsernamePasswordAuthenticationFilter
        return http.build(); // Izgradi i vrati konfigurirani sigurnosni lanac filtera
    }

}
