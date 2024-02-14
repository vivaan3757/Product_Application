package com.pralav.productapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApplicationConfig {

    @Bean
    public RestTemplate createRestTemplaate() {
        return new RestTemplate();
    }
}
