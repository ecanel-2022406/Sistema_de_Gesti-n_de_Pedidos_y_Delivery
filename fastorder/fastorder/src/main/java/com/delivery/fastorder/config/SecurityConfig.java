package com.delivery.fastorder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // 1. Rutas públicas de autenticación (Login y Registro)
                        .requestMatchers("/api/v1/auth/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/api/v1/comercios/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/comercios/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/comercios/**").hasAuthority("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/api/v1/pedidos/**").hasAnyAuthority("CLIENTE", "ADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/v1/pedidos/**").hasAnyAuthority("REPARTIDOR", "ADMIN", "CLIENTE")

                        .requestMatchers(HttpMethod.GET, "/api/v1/comercios/**").permitAll()

                        .anyRequest().authenticated()
                );

        return http.build();
    }
}