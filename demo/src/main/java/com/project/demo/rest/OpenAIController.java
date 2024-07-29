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
     public OpenAIController(OpenAIService openAIService) {
         this.openAIService = openAIService;
     }

    @GetMapping
    public String getTravelSuggestions(String query) throws JsonProcessingException {
        return openAIService.generateTravelSuggestions(query, "You are a travel expert, give suggestions to a user who is looking for travel advice based on the clothing needed, cultural aspects, necessary supplies, security aspects and any other advice you think is good for this country. Return a short response in a paragraph. If country not specied, return a request to type country.");
    }
}

//You are a travel expert, give suggestions to a user who is looking for travel advice based on the clothing needed, cultural aspects, necessary supplies, security aspects and any other advice you think is good for this country.