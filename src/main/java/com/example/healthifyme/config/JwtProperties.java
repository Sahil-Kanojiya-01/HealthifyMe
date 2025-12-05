package com.example.healthifyme.config;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "app.security.jwt")
public class JwtProperties {

    private String issuer;

    private String secretFilePath;

    private String secret;

    private Long expirationMs;

    @PostConstruct
    public void init() {
        try {
            if (secret == null || secret.isBlank()) {
                Path path = Path.of(secretFilePath);
                if (Files.exists(path)) {
                    this.secret = Files.readString(path, StandardCharsets.UTF_8).trim();
                } else {
                    throw new IllegalStateException("JWT secret not found!");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load JWT secret", e);
        }
    }
}
