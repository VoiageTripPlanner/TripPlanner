package com.project.demo.rest;

import com.project.demo.entity.Trip;
import com.project.demo.entity.request.TripDTO;
import com.project.demo.logic.TripService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/trip")
public class TripController implements IController<Trip, Integer>{
    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

//    @PostMapping
//    public ResponseEntity<TripDTO> createTrip(@RequestBody TripDTO tripDTO) {
//        return ResponseEntity.ok(tripService.deconstructTripDTO(tripDTO));
//    }

    @Override
    @PostMapping
    public ResponseEntity<Trip> create(@RequestBody Trip entity) {
        return ResponseEntity.ok(tripService.save(entity));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Trip>> retrieveAll() {

        return ResponseEntity.ok(tripService.findAll());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Trip> retrieveById(@PathVariable Integer integer) {

        return ResponseEntity.ok(tripService.findById(integer));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Trip> update(@RequestBody Trip entity) {

        return ResponseEntity.ok(tripService.save(entity));
    }

    @Override
    public ResponseEntity<Void> deleteById(@PathVariable Integer integer) {
        tripService.deleteById(integer);
        return ResponseEntity.ok().build();
    }
}
