package com.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain (ServerHttpSecurity http) {

        http
                .authorizeExchange()
                .pathMatchers("/api/**").hasRole("USER")
                .pathMatchers("/api/dsds").hasRole("RECRUITER")
                .anyExchange().permitAll()
                .and()
                .oauth2Login(); // to redirect to oauth2 login page.

        return http.build();
    }

}