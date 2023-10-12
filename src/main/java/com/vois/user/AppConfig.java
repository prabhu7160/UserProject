package com.vois.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.vois.user.services.impl.UserDetailsServiceImpl;

@Configuration
class AppConfig {
    @Bean
    public UserDetailsService userDetailsService() {
//        UserDetails user = User.builder().
//                username("prabhu")
//                .password(passwordEncoder().encode("root")).roles("ADMIN").
//                build();
        return new UserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
    	DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    	daoAuthenticationProvider.setUserDetailsService(this.userDetailsService());
    	daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    	return daoAuthenticationProvider;
    }
    
    
    
}