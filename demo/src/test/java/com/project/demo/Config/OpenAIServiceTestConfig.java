package com.project.demo.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.demo.logic.request.OpenAIService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OpenAIServiceTestConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public OpenAIService openAIService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        return new OpenAIService(restTemplate, objectMapper);
    }
}