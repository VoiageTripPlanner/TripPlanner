package com.project.demo.logic.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.demo.logic.exceptions.ApiRequestException;
import com.project.demo.logic.exceptions.ApplicationException;
import com.project.demo.logic.exceptions.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
    public String generateTravelSuggestions(String query, String prompt, Map<String, Object> schema) throws JsonProcessingException {
        try {
            Optional<Map<String, Object>> optionalSchema = Optional.ofNullable(schema);

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

            optionalSchema.ifPresent(s -> requestBodyMap.put("response_format", s));

            String requestBody = objectMapper.writeValueAsString(requestBodyMap);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + openAIKey);

            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

            return restTemplate.postForObject(openAIUrl, entity, String.class);
        } catch (JsonProcessingException e){
            throw new InvalidRequestException(
                    "Failed to process the request body.",
                    HttpStatus.BAD_REQUEST,
                    "INVALID_REQUEST",
                    "The request body could not be processed. Please check the request and try again.",
                    e
            );
        }  catch (RestClientException e) {
            throw new ApiRequestException(
                    "Failed to communicate with the OpenAI API.",
                    HttpStatus.SERVICE_UNAVAILABLE,
                    "API_COMMUNICATION_ERROR",
                    "There was a problem communicating with the OpenAI API. Please try again later.",
                    e
            );
        } catch (Exception e) {
            throw new ApplicationException(
                    "An unexpected error occurred.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "INTERNAL_SERVER_ERROR",
                    "An unexpected error occurred. Please try again later.",
                    e
            );
        }
  }
}
