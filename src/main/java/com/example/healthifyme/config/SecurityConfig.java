package com.example.healthifyme.config;

import com.example.healthifyme.security.JwtUserAuthFilter;
import com.example.healthifyme.security.XJwtAdminAuthFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtUserAuthFilter jwtUserAuthFilter,
                                           XJwtAdminAuthFilter xJwtAdminAuthFilter) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(securitySessionManagementConfigurer -> securitySessionManagementConfigurer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
                                .requestMatchers("/api/**")
                                .authenticated()
                                .requestMatchers("/api/v1/auth/**")
                                .permitAll()
                                .requestMatchers("/resources/**")
                                .permitAll()
                                .anyRequest()
                                .permitAll())
                .addFilterBefore(jwtUserAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(xJwtAdminAuthFilter, JwtUserAuthFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(@Value("${app.security.password-encoder.strength}") int strength) {
        return new BCryptPasswordEncoder(strength);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
                                                         PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
