package com.project.demo.logic;

import com.project.demo.entity.Flight;
import com.project.demo.repository.FlightRepository;
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
public class FlightService implements IService<Flight, Long> {
    @Autowired
    private FlightRepository flightRepository;

//    @Value("${gflights.api.key}")
//    private String apiKey;
//
//    private final RestTemplate restTemplate;
//
//
//    public FlightService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//
//    public String searchFlights(String engine, String departureId,
//                                String arrivalId, Date outboundDate,
//                                int type, Date returnDate) {
//
//        String baseUrl = "https://serpapi.com/search";
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl)
//                .queryParam("engine", engine)
//                .queryParam("type", type)
//                .queryParam("departure_id", departureId)
//                .queryParam("arrival_id", arrivalId)
//                .queryParam("outbound_date", outboundDate)
//                .queryParam("api_key", apiKey);
//
//        if (type == 1 && returnDate != null) {
//            builder.queryParam("return_date", dateFormat.format(returnDate));
//        }
//
//
//        return restTemplate.getForObject(builder.toUriString(), String.class);
//    }

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
    public Optional<Flight> findById(Long aLong) {
        return flightRepository.findById(aLong);
    }

    @Override
    public Flight update(Flight entity) {
        return flightRepository.save(entity);
    }

    @Override
    public void deleteById(Long aLong) {
        flightRepository.deleteById(aLong);
    }



}
