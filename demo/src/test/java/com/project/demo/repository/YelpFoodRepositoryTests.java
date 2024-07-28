package com.project.demo.repository;

import com.project.demo.logic.request.FoodYelpRequestService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static org.mockito.Mockito.when;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public class YelpFoodRepositoryTests {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private FoodYelpRequestService foodService;

    @Value("${activitiesYelpApi.key}")
    private String activitiesApiKeyYelp;

    private final String activitiesKey = activitiesApiKeyYelp;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearchHotels() throws Exception {
        Float latitude = 35.659107F;
        Float longitude = 139.700343F;


        String expectedResponse = "mocked response";

        String baseUrl = "https://api.yelp.com/v3/businesses/search?term=delis";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("latitude", latitude)
                .queryParam("longitude", longitude);


        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(activitiesKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        when(restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class))
                .thenReturn(ResponseEntity.ok(expectedResponse));

        String actualResponse = foodService.searchFood(latitude, longitude);

        Assertions.assertThat(actualResponse).isEqualTo(expectedResponse);
    }

}
