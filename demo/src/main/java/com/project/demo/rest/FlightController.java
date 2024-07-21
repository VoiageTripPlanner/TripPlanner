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

    @GetMapping("search/")
    public ResponseEntity<?> searchFlights(@RequestBody Query query) {
        try {
            Response response = flightService.searchAndFetchFlights(engine, departureId, arrivalId, outboundDate, type, returnDate);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PostMapping(" ")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Flight create(@RequestBody Flight entity) {
        return flightService.save(entity);
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Optional<Flight> retrieveById(Integer aInteger) {
        return flightService.findById(aInteger);
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public void deleteById(Integer aInteger) {
        flightService.deleteById(aInteger);
    }











    @Override
    @GetMapping("/ ")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<Flight> retrieveAll() { return null; }
    @Override
    @PutMapping("/ ")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Flight update(Flight entity) {
        return null;
    }
}
