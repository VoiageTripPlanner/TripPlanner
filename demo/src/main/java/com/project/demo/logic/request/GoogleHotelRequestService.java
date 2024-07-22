package com.project.demo.logic.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class GoogleHotelRequestService {

    @Value("${GOOGLE_HOTEL_KEY}")
    private String googleApiKey;

    @Autowired
    private RestTemplate restTemplate;

    public String searchHotels(String query, Date checkInDate, Date checkOutDate) {
        String baseUrl = "https://serpapi.com/search?engine=google_hotels";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("q", query)
                .queryParam("check_in_date", dateFormat.format(checkInDate))
                .queryParam("check_out_date", dateFormat.format(checkOutDate))
                .queryParam("api_key", googleApiKey);

        return restTemplate.getForObject(builder.toUriString(), String.class);
    }

}
