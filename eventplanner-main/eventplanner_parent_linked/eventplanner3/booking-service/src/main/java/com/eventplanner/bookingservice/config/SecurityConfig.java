package com.eventplanner.bookingservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/h2-console/**").permitAll() // H2 console herkese açık
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable()) // H2 console için CSRF devre dışı
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable())) // H2 console iframe için izin
                .httpBasic(Customizer.withDefaults()); // Basic Auth aktif

        return http.build();
    }
}
