package com.project.demo.repository.Flight;

import com.project.demo.entity.Flights.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Integer> {

}
