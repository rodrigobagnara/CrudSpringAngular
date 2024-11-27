package com.myApi.app.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;

import com.myApi.app.security.FilterSecurityToken;

@Component
public class SecurityFilterChainConfigure {

    @Autowired
    private FilterSecurityToken filterSecurityToken;

    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(csrf -> csrf.disable()).cors(cors -> cors.configurationSource(configure ->
                {
                    var corsConfig = new CorsConfiguration();
                    corsConfig.setAllowedOrigins(List.of("http://localhost:4200", "http://localhost:8080"));
                    corsConfig.setAllowedMethods(List.of("GET","POST", "PUT", "DELETE", "OPTIONS"));
                    corsConfig.setAllowedHeaders(List.of("*"));
                    return corsConfig;
                }))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests.requestMatchers(HttpMethod.POST, "usuario/login", "usuario").permitAll())
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests.anyRequest().authenticated())
                .addFilterBefore(filterSecurityToken, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
