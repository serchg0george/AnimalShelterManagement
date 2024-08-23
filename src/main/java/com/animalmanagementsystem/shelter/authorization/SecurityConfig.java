package com.animalmanagementsystem.shelter.authorization;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String ADMIN_ROLE = "ADMIN";
    public static final String USER_ROLE = "USER";

    private final MyUserDetailService userDetailService;

    public SecurityConfig(MyUserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers("/api/v1/home", "/api/v1/login/**").permitAll();
                    registry.requestMatchers("/api/v1/animal/**").hasRole(ADMIN_ROLE);
                    registry.requestMatchers("/api/v1/user/**").hasRole(ADMIN_ROLE);
                    registry.requestMatchers("/api/v1/role/**").hasRole(ADMIN_ROLE);
                    registry.requestMatchers("/api/v1/user_animal/**").hasRole(ADMIN_ROLE);
                    registry.requestMatchers("/api/v1/user_role/**").hasRole(ADMIN_ROLE);
                    registry.requestMatchers("/api/v1/animal/**").hasRole(USER_ROLE);
                    registry.requestMatchers("/api/v1/cage/**").hasRole(USER_ROLE);
                    registry.requestMatchers("/api/v1/health/**").hasRole(USER_ROLE);
                    registry.anyRequest().authenticated();
                })
                .formLogin(httpSecurityFormLoginConfigurer -> {
                    httpSecurityFormLoginConfigurer.loginPage("api/v1/login").permitAll();
                })
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
