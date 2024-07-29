package com.project.demo.rest;

import com.project.demo.entity.Location;
import com.project.demo.logic.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/locations")
public class LocationController implements IController<Location, Integer>{
    @Autowired
    private LocationService locationService;

    @Override
    @PostMapping("")
    public Location create(@RequestBody Location entity) {
        return locationService.save(entity);
    }

    @Override
    @GetMapping("")
    public List<Location> retrieveAll() {
        return locationService.findAll();
    }

    @Override
    @GetMapping("{id}")
    public Optional<Location> retrieveById(@PathVariable Integer integer) {
        return locationService.findById(integer);
    }

    @Override
    @PutMapping("")
    public Location update(@RequestBody Location entity) {
        return locationService.save(entity);
    }

    @Override
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer integer) {
        locationService.deleteById(integer);
    }
}
