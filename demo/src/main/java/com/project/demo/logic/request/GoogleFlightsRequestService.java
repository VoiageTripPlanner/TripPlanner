package com.project.demo.logic.request;

import com.project.demo.entity.Flights.Response;
import com.project.demo.entity.request.GoogleFlightsRequestEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.SimpleDateFormat;
@Service
public class GoogleFlightsRequestService {

    @Value("${gflightsapikey}")
    private String apiKey;

    private RestTemplate restTemplate;

    public GoogleFlightsRequestEntity searchAndFetchFlights(GoogleFlightsRequestEntity query) throws Exception {
        String baseUrl = "https://serpapi.com";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("engine", "google_flights")
                .queryParam("type", query.getType())
                .queryParam("departure_id", query.getFlights().getFirst().getDepartureId())
                .queryParam("arrival_id", query.getFlights().getFirst().getArrivalId())
                .queryParam("outbound_date", dateFormat.format(outboundDate))
                .queryParam("api_key", apiKey);

        if (type == 1 && returnDate != null) {
            builder.queryParam("return_date", dateFormat.format(returnDate));
        }

        return restTemplate.getForObject(builder.toUriString(), String.class);
    }
}
