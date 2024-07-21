package com.project.demo.repository;
import static org.mockito.Mockito.when;

import com.project.demo.logic.request.GoogleHotelRequestService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GoogleHotelRepositoryTests {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private GoogleHotelRequestService hotelService;

    @Value("${googleHotelApi.key}")
    private String googleApiKeySerp;

    private final String googleApiKey = googleApiKeySerp;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearchHotels() throws Exception {
        String query = "New York";
        Date checkInDate = new SimpleDateFormat("yyyy-MM-dd").parse("2024-08-01");
        Date checkOutDate = new SimpleDateFormat("yyyy-MM-dd").parse("2024-08-10");
        String expectedResponse = "mocked response";

        String baseUrl = "https://serpapi.com/search?engine=google_hotels";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("q", query)
                .queryParam("check_in_date", dateFormat.format(checkInDate))
                .queryParam("check_out_date", dateFormat.format(checkOutDate))
                .queryParam("api_key", googleApiKey);

        when(restTemplate.getForObject(builder.toUriString(), String.class)).thenReturn(expectedResponse);

        String actualResponse = hotelService.searchHotels(query, checkInDate, checkOutDate);

        Assertions.assertThat(actualResponse).isEqualTo(expectedResponse);
    }

}
