package com.delivery.fastorder.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;
import java.util.Collections;

public class JwtTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            try {

                byte[] decodedBytes = Base64.getDecoder().decode(token);
                String decodedString = new String(decodedBytes);
                String[] parts = decodedString.split(":");

                if (parts.length >= 2) {
                    String email = parts[0];
                    String rol = parts[1];

                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(rol);

                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            email, null, Collections.singletonList(authority)
                    );

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (Exception e) {

            }
        }
        filterChain.doFilter(request, response);
    }
}