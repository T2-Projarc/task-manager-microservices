package com.example.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desabilita CSRF para simplificação
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll() // Permite acesso às rotas de autenticação
                .requestMatchers("/actuator/**").permitAll() // Permite acesso às rotas do Actuator
                .requestMatchers("/").permitAll() // Permite acesso à raiz
                .requestMatchers("/h2-console/**").permitAll() // Permite acesso ao console H2
                .requestMatchers("/swagger-ui/**").permitAll() // Permite acesso ao Swagger UI
                .requestMatchers("/v3/api-docs/**").permitAll() // Permite acesso à documentação da API
                .anyRequest().authenticated() // Protege todas as outras rotas
            )
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable())) // Necessário para H2 console
            .httpBasic(httpBasic -> httpBasic.disable()); // Desabilita autenticação HTTP básica

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
