package com.example.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http){
        //To define which URL paths should be secured and which should not
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("api/hello").permitAll()
                .anyRequest().authenticated()
        );

        return http.build();
    }
}
