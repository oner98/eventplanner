package com.eventplanner.eventservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // 🔴 H2 console için ZORUNLU
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers
                        .frameOptions(frame -> frame.disable())
                )

                .authorizeHttpRequests(auth -> auth
                        // ✅ H2 + Actuator serbest
                        .requestMatchers(
                                "/h2-console/**",
                                "/actuator/health",
                                "/actuator/info"
                        ).permitAll()

                        // 🔐 Diğer her şey auth ister
                        .anyRequest().authenticated()
                )

                // ✅ DEPRECATED DEĞİL (Spring Security 6 uyumlu)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
