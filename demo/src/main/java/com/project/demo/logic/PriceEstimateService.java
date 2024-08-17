package com.project.demo.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.demo.entity.request.OpenAIResponse;
import com.project.demo.entity.request.PriceEstimate;
import com.project.demo.logic.exceptions.ApiRequestException;
import com.project.demo.logic.exceptions.InvalidRequestException;
import com.project.demo.logic.request.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PriceEstimateService {

    @Autowired
    private OpenAIService openAIService;

    public PriceEstimate calculatePriceEstimate(PriceEstimate priceEstimate) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String query = objectMapper.writeValueAsString(priceEstimate);

            String prompt = "You are an expert in tourism worldwide, especially in restaurants and activities. You will receive the following input in JSON format:\n" +
                    "{\n" +
                    "  \"startDate\": \"YYYY-MM-DD\",\n" +
                    "  \"endDate\": \"YYYY-MM-DD\",\n" +
                    "  \"currency\": \"Currency code (e.g., USD)\",\n" +
                    "  \"totalEstimate\": 100.0,\n" +
                    "  \"destination\": \"City/Country\",\n" +
                    "  \"activityEstimates\": [\n" +
                    "    {\n" +
                    "      \"name\": \"Restaurant Name\",\n" +
                    "      \"address\": \"Restaurant Address\",\n" +
                    "      \"location\": \"Location\",\n" +
                    "      \"priceEstimate\": \"Price estimate\"\n" +
                    "    }\n" +
                    "  ]\n" +
                    "  ]\n" +
                    "}\n" +
                    "\n" +
                    "Based on historical data for the provided date and destination, return a JSON object with the same structure that you receive as input.\n" +
                    "Only, make sure you update the totalEstimate with the sum of the full estimate, and the priceEstimate of each activity or restaurant. Please give real accurate price estimates."
                    +
                    "\n" +
                    "Ensure the JSON structure is strictly followed.";

            String openAIResponseString = openAIService.generateTravelSuggestions(query, prompt, null);
            OpenAIResponse openAIResponse = objectMapper.readValue(openAIResponseString, OpenAIResponse.class);

            String content = openAIResponse.getChoices().get(0).getMessage().getContent();
            content = content.replace("```json", "").replace("```", "").trim();

            return objectMapper.readValue(content, PriceEstimate.class);
        } catch (JsonProcessingException e) {
            throw new InvalidRequestException("Error processing JSON", HttpStatus.BAD_REQUEST, "JSON_PROCESSING_ERROR", "Invalid JSON format", e);
        } catch (Exception e) {
            throw new ApiRequestException("Error calculating price estimate", HttpStatus.INTERNAL_SERVER_ERROR, "PRICE_ESTIMATE_ERROR", "An error occurred while calculating the price estimate", e);
        }
    }
}
