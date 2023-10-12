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
@EnableWebSecurity
public class SecurityConfig{

    @Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;
    
    public static final String[] PUBLIC_URLS= {
			"/auth/login",
			"/swagger-resources/**",
			"/swagger-ui/**",
			"/user/authenticate",
			"/user/register",
			"/admin/authenticate",
			"/admin/register",
			"/webjars/**"};

    public static final String[] AUTHENTICATED_URLS= {
    		"/user/authenticate",
			"/user/register",
			"/admin/authenticate",
			"/admin/register",
			"/user/**",
			"/admin/**"};
    
    @Bean
    public SecurityFilterChain securityFilterChaining(HttpSecurity http) throws Exception {

    	http.csrf(csrf -> csrf.disable())
        .cors(cors -> cors.disable())
        .authorizeHttpRequests(auth ->
            auth
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/userproject/**").hasRole("USER")
                .requestMatchers("/**").permitAll()
        )
        .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
    }

    @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception{
    	return new JwtAuthenticationFilter();
    }
    
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception{
//    	return this.authenticationManagerBean();
//    }
    
    }