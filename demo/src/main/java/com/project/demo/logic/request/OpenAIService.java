package com.project.demo.logic.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class OpenAIService {
    @Value("${openai.api.key}")
    private String openAIKey;
    @Value("${openai.api.url}")
    private String openAIUrl;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    @Autowired
    public OpenAIService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }
    public String generateTravelSuggestions(String query, String prompt) throws JsonProcessingException {
        Map<String, Object> systemContent = new HashMap<>();
        systemContent.put("type", "text");
        systemContent.put("text", prompt);

        Map<String, Object> userContent = new HashMap<>();
        userContent.put("type", "text");
        userContent.put("text", query);

        Map<String, Object>[] messages = new Map[]{
                new HashMap<String, Object>() {{
                    put("role", "system");
                    put("content", Arrays.asList(systemContent));
                }},
                new HashMap<String, Object>() {{
                    put("role", "user");
                    put("content", Arrays.asList(userContent));
                }}
        };

        Map<String, Object> requestBodyMap = new HashMap<>();
        requestBodyMap.put("model", "gpt-4o-mini");
        requestBodyMap.put("messages", messages);
        requestBodyMap.put("temperature", 1);
        requestBodyMap.put("max_tokens", 1000);
        requestBodyMap.put("top_p", 1);
        requestBodyMap.put("frequency_penalty", 0);
        requestBodyMap.put("presence_penalty", 0);

        String requestBody = objectMapper.writeValueAsString(requestBodyMap);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + openAIKey);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        return restTemplate.postForObject(openAIUrl, entity, String.class);
    }
}
