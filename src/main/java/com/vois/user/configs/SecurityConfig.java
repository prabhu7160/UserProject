package com.vois.user.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.vois.user.security.JwtAuthenticationEntryPoint;
import com.vois.user.security.JwtAuthenticationFilter;


@Configuration
@EnableWebMvc
public class SecurityConfig{


    @Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;
    
    public static final String[] PUBLIC_URLS= {
			"/auth/login",
			"/v2/api-docs",
			"/swagger-resources/**",
			"/swagger-ui/**",
			"/webjars/**"};

    @Bean
    public SecurityFilterChain securityFilterChaining(HttpSecurity http) throws Exception {

    	http.csrf(csrf -> csrf.disable())
        .cors(cors -> cors.disable())
        .authorizeHttpRequests(auth ->
            auth.requestMatchers("/user/**").authenticated() // Add this line to permit /user endpoints
                .requestMatchers(PUBLIC_URLS).permitAll()
                .anyRequest().authenticated()
        )
        .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
    }

    }