package com.project.demo.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.demo.logic.request.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/openai")
public class OpenAIController {
    @Autowired
    private OpenAIService openAIService;

     @Autowired
     private RestTemplate restTemplate;


    @GetMapping
    public Object getTravelSuggestions(@RequestParam String query) throws JsonProcessingException {
        return openAIService.generateTravelSuggestions(query, "What time is it");
    }
}
