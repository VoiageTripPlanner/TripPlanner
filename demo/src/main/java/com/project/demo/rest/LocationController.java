package com.project.demo.rest;

import com.project.demo.entity.Location;
import com.project.demo.logic.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/locations")
public class LocationController implements IController<Location, Integer> {
    @Autowired
    private LocationService locationService;

    @Override
    @PostMapping
    public ResponseEntity<Location> create(@RequestBody Location entity) {
        Location createdLocation = locationService.save(entity);
        return ResponseEntity.ok(createdLocation);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Location>> retrieveAll() {
        List<Location> locations = locationService.findAll();
        return ResponseEntity.ok(locations);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Location> retrieveById(@PathVariable Integer id) {
        Location location = locationService.findById(id);
        return ResponseEntity.ok(location);
    }

    @Override
    @PutMapping
    public ResponseEntity<Location> update(@RequestBody Location entity) {
        Location updatedLocation = locationService.save(entity);
        return ResponseEntity.ok(updatedLocation);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        locationService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}