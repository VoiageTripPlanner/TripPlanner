package com.project.demo.logic.request;

import com.project.demo.entity.request.GoogleFlightsRequestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GoogleFlightsRequestService {

    @Value("${GOOGLE_FLIGHTS_API_KEY}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    public GoogleFlightsRequestEntity searchFlights(GoogleFlightsRequestEntity query) throws Exception {
        try {
            String baseUrl = "https://serpapi.com/search/?engine=google_flights";
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                    .queryParam("departure_id", query.getDeparture_id())
                    .queryParam("arrival_id", query.getArrival_id())
                    .queryParam("type", query.getType())
                    .queryParam("outbound_date", query.getOutbound_date())
                    .queryParam("api_key", apiKey);

            addQueryParameters(builder, query);

            return restTemplate.getForObject(builder.toUriString(), GoogleFlightsRequestEntity.class);

        } catch (RestClientException e) {
            throw new Exception("Failed to communicate with the flight API.", e);
        } catch (Exception e) {
            throw new Exception("An unexpected error occurred while searching for flights.", e);
        }
    }

    private void addQueryParameters(UriComponentsBuilder builder, GoogleFlightsRequestEntity query) throws Exception {
            if (query.getType() == 1 && query.getReturn_date() != null) {
                builder.queryParam("return_date", query.getReturn_date());
            } else if (query.getType() == 2 && query.getReturn_date() == null) {

            } else if (query.getType() != 1 && query.getType() != 2) {
                throw new IllegalArgumentException("Invalid type value.");
            }

            if (query.getTravel_class() >= 1 && query.getTravel_class() <= 4) {
                builder.queryParam("travel_class", String.valueOf(query.getTravel_class()));
            }
            if (query.getStops() >= 0 && query.getStops() <= 3) {
                builder.queryParam("stops", String.valueOf(query.getStops()));
            }
            if (query.getMax_price() != 0) {
                builder.queryParam("max_price", String.valueOf(query.getMax_price()));
            }
            if (query.getHl() != null) {
                builder.queryParam("hl", String.valueOf(query.getHl()));
            }
            if (query.getGl() != null) {
                builder.queryParam("gl", String.valueOf(query.getGl()));
            }
            if (query.getCurrency() != null) {
                builder.queryParam("currency", String.valueOf(query.getCurrency()));
            }
    }
}


