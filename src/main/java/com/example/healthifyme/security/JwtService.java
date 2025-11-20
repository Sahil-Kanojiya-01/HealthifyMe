package com.example.healthifyme.security;

import com.example.healthifyme.config.JwtProperties;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.*;

@Slf4j
@Service
public class JwtService {

    private final JwtProperties props;
    private final Key key;

    public JwtService(
            JwtProperties props
    ) {
        this.props = props;
//        byte[] keyBytes = props.getSecret().getBytes();
        byte[] keyBytes =  "AlphaBetaGammaDeltaSigmaTheta1234".getBytes(StandardCharsets.UTF_8);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String email) {
        log.debug("Generating token for User: {}", email);
        Map<String, Object> claims = new HashMap<>();

        Instant issuedAt = Instant.now();
        Instant expiration = issuedAt.plusMillis(props.getExpirationMs());

        return Jwts.builder().setClaims(claims).setSubject(email).setIssuer(props.getIssuer()).setIssuedAt(Date.from(issuedAt)).setExpiration(Date.from(expiration)).signWith(key, SignatureAlgorithm.HS256).compact();
    }

    public boolean validate(String token) {
        log.debug("Validating token ({} chars)", token.length());
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public String extractEmail(String token) {
        log.debug("Extracting token ({} chars)", token.length());
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }
}
