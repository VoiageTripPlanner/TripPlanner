package com.project.demo.rest;

import com.project.demo.entity.Flight;
import com.project.demo.logic.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("flights/")
public class FlightController implements IController<Flight, Integer> {
    @Autowired
    private FlightService flightService;

    @Override
    @PostMapping(" ")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Flight create(@RequestBody Flight entity) {
        return flightService.save(entity);
    }

    @Override
    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public Optional<Flight> retrieveById(Integer aInteger) {
        return flightService.findById(aInteger);
    }

    @Override
    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public void deleteById(Integer aInteger) {
        flightService.deleteById(aInteger);
    }

    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------*/
    @Override
    public List<Flight> retrieveAll() { return null; }
    @Override
    public Flight update(Flight entity) {
        return null;
    }
}
