package com.example.healthifyme.config;

import com.resend.Resend;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class MailConfig {

    private final MailProperties mailProperties;

    @Bean
    public Resend resend(){
        return new Resend(mailProperties.getApiKey());
    }
}
