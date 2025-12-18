package com.eventplanner.EventPlanner.security;


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
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}


//package com.eventplanner.EventPlanner.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http
//                // CSRF kapalı (API & Postman için)
//                .csrf(csrf -> csrf.disable())
//
//                // TÜM endpoint'ler serbest
//                .authorizeHttpRequests(auth -> auth
//                        .anyRequest().permitAll()
//                )
//
//                // Login sayfasını kapat
//                .formLogin(form -> form.disable())
//
//                // Basic Auth kapalı
//                .httpBasic(basic -> basic.disable());
//
//        return http.build();
//    }
//}
