package com.example.healthifyme.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthFilter extends OncePerRequestFilter{
    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    private static final String BEARER_TOKEN_START_CONDITION = "Bearer ";
    private static final String DEFAULT_ROLE = "ROLE_USER";
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String path = request.getRequestURI();
        log.debug("Incoming request: {}", path);
        final String authHeader = request.getHeader(AUTHORIZATION_HEADER_KEY);
        if (authHeader == null || !authHeader.startsWith(BEARER_TOKEN_START_CONDITION)) {
            log.warn("No Bearer token found in Authorization header for {}", path);
            filterChain.doFilter(request, response);
            return;
        }
        String token = authHeader.substring(7);
        log.debug("Extracted JWT token ({} chars)", token.length());
        if (!jwtService.validateToken(token)) {
            log.warn("Invalid JWT token on path: {}", path);
            filterChain.doFilter(request, response);
            return;
        }
        String email = jwtService.extractEmail(token);
        log.info("Valid JWT for user: {}", email);
        var auth = new UsernamePasswordAuthenticationToken(email, null,
            Collections.singleton(new SimpleGrantedAuthority(DEFAULT_ROLE)));
        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(auth);
        log.debug("Authentication set in SecurityContext for user: {}", email);
        filterChain.doFilter(request, response);
    }
}
