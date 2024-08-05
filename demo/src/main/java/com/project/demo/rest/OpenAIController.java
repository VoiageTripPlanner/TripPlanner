package com.project.demo.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.demo.logic.OpenAIService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/openai")
public class OpenAIController {
    private final OpenAIService openAIService;
    public OpenAIController(OpenAIService openAIService) {
         this.openAIService = openAIService;
     }

    @GetMapping
    public ResponseEntity<String> getTravelSuggestions(String query){
        return ResponseEntity.ok(openAIService.generateTravelSuggestions(query, "You are a travel expert, give suggestions to a user who is looking for travel advice based on the clothing needed, cultural aspects, necessary supplies, security aspects and any other advice you think is good for this country. Return a short response in a paragraph. If country not specified, return a request to type country."));
    }
}

//You are a travel expert, give suggestions to a user who is looking for travel advice based on the clothing needed, cultural aspects, necessary supplies, security aspects and any other advice you think is good for this country.