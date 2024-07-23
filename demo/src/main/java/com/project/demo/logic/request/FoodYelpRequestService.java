package com.project.demo.logic.request;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import org.springframework.http.ResponseEntity;

import org.slf4j.Logger;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FoodYelpRequestService {

    private static final Logger logger = LoggerFactory.getLogger(FoodYelpRequestService.class);

    @Value("${foodYelpApi.key}")
    private String yelpApiKey;
    @Autowired
    private RestTemplate restTemplate;

    public String searchActivities(Float latitude, Float longitude) {

        try{


            String baseUrl = "https://api.yelp.com/v3/businesses/search?term=delis";

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                    .queryParam("latitude", latitude)
                    .queryParam("longitude", longitude);

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(yelpApiKey);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.GET,
                    entity,
                    String.class
            );

            if (!response.getStatusCode().is2xxSuccessful()) {
                String errorBody = response.getBody();
                if (errorBody != null) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode errorNode = objectMapper.readTree(errorBody);
                    logger.error("API Error: {}", errorNode.toString());
                } else {
                    logger.error("API Error: {}", response.getStatusCode());
                }
                return "There is no response, please contact the administrator";
            }

            return response.getBody();
        }
        catch (Exception e) {

            logger.error("Error processing the petition",e);

            return "There is no response from the API service";
        }


    }

}
