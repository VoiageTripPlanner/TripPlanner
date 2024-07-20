package com.project.demo.logic.request;


import com.project.demo.entity.request.GoogleHotelRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class GoogleHotelRequestService {

    @Value("${googleHotelApi.key}")
    private String googleApiKey;
    private final RestTemplate restTemplate;
    public GoogleHotelRequestService (RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String searchHotels(String engine, String query,Date check_in_date,Date check_out_date) {

        String baseUrl = "https://serpapi.com/search";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("engine", engine)
                .queryParam("q", query)
                .queryParam("check_in_date", check_in_date)
                .queryParam("check_out_date", check_out_date)
                .queryParam("api_key", googleApiKey);

        return restTemplate.getForObject(builder.toUriString(), String.class);
    }

    public GoogleHotelRequest listQueryFlights(){

        return null;
    }


}
