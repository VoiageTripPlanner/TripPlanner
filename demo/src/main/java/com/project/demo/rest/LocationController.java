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
    @PostMapping("/")
    public Location create(Location entity) {
        return locationService.save(entity);
    }

    @Override
    @GetMapping("/")
    public List<Location> retrieveAll() {
        return locationService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public Optional<Location> retrieveById(Integer integer) {
        return null;
    }

    @Override
    @PutMapping("/")
    public Location update(Location entity) {
        return null;
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteById(Integer integer) {

    }
}
