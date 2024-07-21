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

    public Response searchAndFetchFlights(Query query) throws Exception {
        String baseUrl = "https://serpapi.com";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("engine", "google_flights")
                .queryParam("type", query.getType())
                .queryParam("departure_id", query.getFlights().get)
                .queryParam("arrival_id", arrivalId)
                .queryParam("outbound_date", dateFormat.format(outboundDate))
                .queryParam("api_key", apiKey);

        if (type == 1 && returnDate != null) {
            builder.queryParam("return_date", dateFormat.format(returnDate));
        }

        return objectMapper.readValue(jsonResponse, Response.class);
    }

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



