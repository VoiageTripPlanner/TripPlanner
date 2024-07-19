package com.project.demo.rest;

import com.project.demo.entity.Flight;
import com.project.demo.logic.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController implements IController<Flight, Long> {
    @Autowired
    private FlightService flightService;

    public String searchFlights(@PathVariable String engine, @PathVariable String departureId, @PathVariable String arrivalId,
                                @PathVariable Date outboundDate, @PathVariable int type, @PathVariable Date returnDate) {
        return flightService.searchFlights(engine, departureId, arrivalId, outboundDate, type, returnDate);
    }
    
    @Override
    @PostMapping
    public Flight create(@RequestBody Flight entity) {
        return flightService.save(entity);
    }

    @Override
    @GetMapping
    public List<Flight> retrieveAll() {
        return flightService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public Optional<Flight> retrieveById(Long aLong) {
        return flightService.findById(aLong);
    }

    @Override
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Flight update(Flight entity) {
        return flightService.save(entity);
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void deleteById(Long aLong) {
        flightService.deleteById(aLong);
    }
}
