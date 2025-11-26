package com.example.healthifyme.security;

import com.example.healthifyme.config.JwtProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JwtService{
    private final JwtProperties props;
    private final Key key;
    private final static String ADMIN_X_CLAIM_KEY = "admin-x-token";

    public JwtService(JwtProperties props) {
        this.props = props;
        byte[] keyBytes = props.getSecret().getBytes(StandardCharsets.UTF_8);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String email) {
        log.debug("Generating token for User: {}", email);
        Map<String, Object> claims = new HashMap<>();
        Instant issuedAt = Instant.now();
        Instant expiration = issuedAt.plusMillis(props.getExpirationMs());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuer(props.getIssuer())
                .setIssuedAt(Date.from(issuedAt))
                .setExpiration(Date.from(expiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateAdminXToken(String email) {
        log.debug("Generating admin-x-token for Admin: {}", email);
        Map<String, Object> claims = new HashMap<>();
        claims.put(ADMIN_X_CLAIM_KEY, true);
        Instant issuedAt = Instant.now();
        Instant expiration = issuedAt.plusMillis(props.getExpirationMs());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuer(props.getIssuer())
                .setIssuedAt(Date.from(issuedAt))
                .setExpiration(Date.from(expiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        log.debug("Validating token ({} chars)", token.length());
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public boolean validateXToken(String token) {
        log.debug("Validating token ({} chars)", token.length());
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .get(ADMIN_X_CLAIM_KEY, Boolean.class);
        } catch (JwtException e) {
            return false;
        }
    }

    public String extractEmail(String token) {
        log.debug("Extracting token ({} chars)", token.length());
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }
}
