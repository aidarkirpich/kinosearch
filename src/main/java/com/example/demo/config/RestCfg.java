package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestCfg {

    @Bean
    public static RestTemplate getRest(){
        return new RestTemplate();
    }
}
