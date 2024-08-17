package com.project.demo.rest;

import com.project.demo.entity.Trip;
import com.project.demo.logic.TripService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trip")
public class TripController{
    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping
    public ResponseEntity<Trip> create(@RequestBody Trip entity) {
        return ResponseEntity.ok(tripService.save(entity));
    }

    @GetMapping
    public ResponseEntity<List<Trip>> retrieveAll() {

        return ResponseEntity.ok(tripService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trip> retrieveById(@PathVariable Integer id) {

        return ResponseEntity.ok(tripService.findByIdTrip(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trip> update(@RequestBody Trip entity) {

        return ResponseEntity.ok(tripService.save(entity));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        tripService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Trip> updateTrip(@PathVariable Integer id, @RequestBody Trip trip) {
        Trip updatedTrip = tripService.updateTrip(id, trip);
        return ResponseEntity.ok(updatedTrip);
    }
}