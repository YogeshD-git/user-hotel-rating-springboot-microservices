package com.microservice.apigateway.config;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//@Configuration
//@EnableWebFluxSecurity
//public class SecurityConfig {
//	
//	@Bean
//	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
//		httpSecurity.authorizeExchange().anyExchange().
//		authenticated().and().oauth2ResourceServer().jwt();
//		
//		return httpSecurity.build();
//		
//	}
//
//}

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        // Disable authentication and allow all requests
        httpSecurity.csrf().disable() // Disable CSRF protection if needed
                    .authorizeExchange()
                    .anyExchange().permitAll(); // Allow all requests without authentication
        
        return httpSecurity.build();
    }
}
