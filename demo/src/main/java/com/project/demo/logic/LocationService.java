package com.project.demo.logic;

import com.project.demo.entity.Location;
import com.project.demo.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService implements IService<Location, Integer>{
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Location save(Location entity) {
        return locationRepository.save(entity);
    }

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public Optional<Location> findById(Integer integer) {
        return null;
    }

    @Override
    public Location update(Location entity) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }
}
