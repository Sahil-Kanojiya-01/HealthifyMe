package com.example.healthifyme.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.ZoneId;

@Configuration
public class TimeConfig {

    private static final ZoneId STANDARD_ZONE = ZoneId.of("UTC");

    @Bean
    public Clock clock(){
        return Clock.system(STANDARD_ZONE);
    }
}
