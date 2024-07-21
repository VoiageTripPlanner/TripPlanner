package com.project.demo.repository.Flight;

import com.project.demo.entity.Flights.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
}
