package com.project.demo.logic.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import org.springframework.http.ResponseEntity;

@Service
public class ActivitiesYelpRequestService {

    @Value("${activitiesYelpApi.key}")
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

            return response.getBody();
        }
        catch (Exception e) {
            return "There is no response from the API service";
        }


    }

}
