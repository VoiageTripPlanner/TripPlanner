package com.project.demo.logic;

import com.project.demo.entity.Flight;
import com.project.demo.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService implements IService<Flight, Long> {
    @Autowired
    private FlightRepository flightRepository;

    @Override
    public Flight save(Flight entity) {
        return flightRepository.save(entity);
    }

    @Override
    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    @Override
    public Optional<Flight> findById(Long aLong) {
        return flightRepository.findById(aLong);
    }

    @Override
    public Flight update(Flight entity) {
        return flightRepository.save(entity);
    }

    @Override
    public void deleteById(Long aLong) {
        flightRepository.deleteById(aLong);
    }
}
