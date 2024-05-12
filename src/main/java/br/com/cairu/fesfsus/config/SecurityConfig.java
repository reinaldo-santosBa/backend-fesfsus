package br.com.cairu.fesfsus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(authorize -> authorize

                // legislacao
                .requestMatchers(HttpMethod.POST, "/legislacao/salvar").permitAll()
                .requestMatchers(HttpMethod.GET, "/legislacao/listar").permitAll()
                .requestMatchers(HttpMethod.GET, "/legislacao/{id}").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/legislacao/{id}").permitAll()
                .requestMatchers(HttpMethod.PUT, "/legislacao/{id}").permitAll()

                // usuario
                .requestMatchers(HttpMethod.POST, "/usuario/salvar").permitAll()
                .requestMatchers(HttpMethod.GET, "/usuario/listar").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/usuario/{id}").permitAll()
                .requestMatchers(HttpMethod.PUT, "/usuario/{id}").permitAll()
                .requestMatchers(HttpMethod.GET, "/usuario/{id}").permitAll()
                .requestMatchers(HttpMethod.POST, "/usuario/email").permitAll()
                .requestMatchers(HttpMethod.POST, "/usuario/nome").permitAll()

                .anyRequest().authenticated())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return httpSecurity.build();
    }

}
