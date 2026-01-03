package com.example.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // CORS を有効化（これが超重要）
            .cors(cors -> {})

            // CSRF 無効（API 用）
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth
                // preflight を明示的に許可
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // その他のリクエストはすべて許可
                .anyRequest().permitAll()
            );

        return http.build();
    }
}
