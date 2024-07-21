package com.project.demo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.demo.entity.Flights.Flight;
import com.project.demo.entity.Flights.Query;
import com.project.demo.entity.Flights.Response;
import com.project.demo.logic.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController implements IController<Flight, Integer> {
    @Autowired
    private FlightService flightService;

    @GetMapping("/search/{engine}/{departureId}/{arrivalId}/{outboundDate}/{type}/{returnDate}")
    public ResponseEntity<?> searchFlights(@RequestParam String engine,
                                           @RequestParam String departureId,
                                           @RequestParam String arrivalId,
                                           @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date outboundDate,
                                           @RequestParam int type, @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date returnDate) {
        try {
            Response response = flightService.searchAndFetchFlights(engine, departureId, arrivalId, outboundDate, type, returnDate);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PostMapping("/ ")
    public Flight create(@RequestBody Flight entity) {
        return flightService.save(entity);
    }

    @Override
    @GetMapping("/ ")
    public List<Flight> retrieveAll() {
        return flightService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public Optional<Flight> retrieveById(Integer aInteger) {
        return flightService.findById(aInteger);
    }

    @Override
    @PutMapping(" ")
    public Flight update(Flight entity) {
        return null;
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public void deleteById(Integer aInteger) {
        flightService.deleteById(aInteger);
    }
}
