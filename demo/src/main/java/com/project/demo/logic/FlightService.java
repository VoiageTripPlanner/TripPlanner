package com.project.demo.logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.demo.entity.Flights.Flight;
import com.project.demo.entity.Flights.Query;
import com.project.demo.entity.Flights.Response;
import com.project.demo.repository.Flight.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService implements IService<Flight, Integer> {
    @Autowired
    private FlightRepository flightRepository;

    @Value("${gflightsapikey}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();


    public FlightService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // En tu FlightService
    public Response searchAndFetchFlights(String engine, String departureId,
                                          String arrivalId, Date outboundDate,
                                          int type, Date returnDate) throws Exception {
        String baseUrl = "https://serpapi.com";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("engine", engine)
                .queryParam("type", type)
                .queryParam("departure_id", departureId)
                .queryParam("arrival_id", arrivalId)
                .queryParam("outbound_date", dateFormat.format(outboundDate))
                .queryParam("api_key", apiKey);

        if (type == 1 && returnDate != null) {
            builder.queryParam("return_date", dateFormat.format(returnDate));
        }

        String jsonResponse = restTemplate.getForObject(builder.toUriString(), String.class);
        return objectMapper.readValue(jsonResponse, Response.class);
    }

    public List<Query> parseFlight(Response response) {
        // Directly return the list of FlightSearch objects from the response
        return response.getOther_flights();
    }

    // Additional methods to process and retrieve specific data from the FlightSearch objects
    // For example, filtering flights by criteria, calculating total prices, etc.

    //Para persistencia de datos de los vuelos
    @Override
    public Flight save(Flight entity) {
        return flightRepository.save(entity);
    }

    @Override
    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    @Override
    public Optional<Flight> findById(Integer aInteger) {
        return flightRepository.findById(aInteger);
    }

    @Override
    public Flight update(Flight entity) {
        return flightRepository.save(entity);
    }

    @Override
    public void deleteById(Integer aInteger) {
        flightRepository.deleteById(aInteger);
    }
}



