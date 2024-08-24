package com.project.demo.rest;

import com.project.demo.entity.Trip;
import com.project.demo.entity.request.PaginationRequest;
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

    @GetMapping("/pagination/{userId}")
    public ResponseEntity<PaginationRequest<List<Trip>>> retrieveByPage(@PathVariable Integer userId,
                                                                        @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                        @RequestParam(name = "size", defaultValue = "10") Integer size) {

        return ResponseEntity.ok(tripService.findAllByPage(userId, page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trip> retrieveById(@PathVariable Integer id) {

        return ResponseEntity.ok(tripService.findByIdTrip(id));
    }

    //@PutMapping("/{id}")
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

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Trip>> retrieveByUserId(@PathVariable Integer id) {
        return ResponseEntity.ok(tripService.findByUserId(id));
    }
}