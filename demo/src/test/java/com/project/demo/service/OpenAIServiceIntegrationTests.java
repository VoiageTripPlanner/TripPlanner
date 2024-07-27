package com.project.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.demo.Config.OpenAIServiceTestConfig;
import com.project.demo.logic.request.OpenAIService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration(classes = {OpenAIServiceTestConfig.class})
public class OpenAIServiceIntegrationTests {

    @Autowired
    private OpenAIService openAIService;

    @Value("${openai.api.key}")
    private String openAIKey;

    @Value("${openai.api.url}")
    private String openAIUrl;

    @BeforeEach
    public void setUp() {
        // No need to initialize beans manually, Spring will inject them
    }

    @Test
    public void testGenerateTravelSuggestions() throws JsonProcessingException {
        String query = "Location: Santa Teresa, Guanacaste, Costa Rica\nDate: December 2024";
        String prompt = "You are an expert in tourism worldwide. Your role is to receive as input a given location and date. You must generate suggestions about clothes, security, weather, cultural, suggested supplies and other conditions to be considered for a person that is going to travel to that place in the given date.\n\nYour only must generate a JSON output and it must have the following keys: clothing, cultural, supply, security and other. Each one of these keys must have a list containing Strings of the recommendations. Make sure the suggestions are concise and brief. This must be displayed on my application and fill a certain card of suggestions, so make sure to write it straight to the point.";

        String actualResponse = openAIService.generateTravelSuggestions(query, prompt);

        assertThat(actualResponse).isNotNull();
        assertThat(actualResponse).contains("clothing");
        assertThat(actualResponse).contains("cultural");
        assertThat(actualResponse).contains("supply");
        assertThat(actualResponse).contains("security");
        assertThat(actualResponse).contains("other");
    }
}