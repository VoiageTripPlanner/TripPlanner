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
                    "Based on historical data for the provided date and destination, calculate the price for those activities or restaurants. Return a JSON object with the same structure that you receive as input.\n" +
                    "But, make sure you update the totalEstimate with the sum of the full estimate, and the priceEstimate of each activity or restaurant. Please give real accurate price estimates.\n" +
                    "Also, in case of restaurants, you need to calculate on 3 meals a day for the totalEstimate. You calculate on an average a meal for that place and dates, the restaurants should not affect the estimation of the average."+
                    "You multiply the average price times total of meals for that trip using 3 meals a day. You need to subtract the restaurants which price was calculated more exactly on the list provided"+
                    "\n.The final formula for the totalEstimate is: totalEstimate = averagePrice * (numberOfMeals - numberOfRestaurantsProvided)+ sum(restaurantPriceEstimates). For activities you only sum the activies."
                    +"\n. If you can't find the price for a specific activity or restaurant, you can use an estimate based on historic data of similar activities in similar places and dates.\n" +
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
