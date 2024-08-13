package com.project.demo.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.demo.logic.request.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
        return openAIService.generateTravelSuggestions(query, "You are a travel expert, give suggestions to a user who is looking for travel advice based on the clothing needed, cultural aspects, necessary supplies, security aspects and any other advice you think is good for this country. Return a short response in a paragraph. If country not specied, return a request to type country.", null);
    }

    @GetMapping("/tripRecommendation")
    public String getTripRecommendation() throws JsonProcessingException {
        String tripRecommendationQuery = "can you give me a touristic destination to go and provide the response as JSON, where country is the country, city is the city, " +
                "reasons as an array of 3 brief reasons to travel there, and description as the description of the destination?";
        String systemPrompt = "You are a travel expert, you should pick a random place and look for the touristic details about it, then response with the JSON asked for the user.";

        Map<String, Object> jsonSchema = new HashMap<>();
        jsonSchema.put("name", "trip_recommendation");
        jsonSchema.put("strict", true);
        jsonSchema.put("schema", new HashMap<String, Object>() {{
            put("type", "object");
            put("properties", new HashMap<String, Object>() {{
                put("country", new HashMap<String, Object>() {{
                    put("type", "string");
                }});
                put("city", new HashMap<String, Object>() {{
                    put("type", "string");
                }});
                put("reasons", new HashMap<String, Object>() {{
                    put("type", "array");
                    put("items", new HashMap<String, Object>() {{
                        put("type", "string");
                    }});
                }});
                put("description", new HashMap<String, Object>() {{
                    put("type", "string");
                }});
            }});
            put("required", new String[]{"country", "city", "reasons", "description"});
            put("additionalProperties", false);
        }});

        Map<String, Object> schemaObject = new HashMap<>();
        schemaObject.put("type", "json_schema");
        schemaObject.put("json_schema", jsonSchema);
        return openAIService.generateTravelSuggestions(tripRecommendationQuery, systemPrompt, schemaObject);
    }
}
