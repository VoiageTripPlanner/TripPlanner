package com.project.demo.repository.Flight;

import com.project.demo.entity.Flights.Layover;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LayoverRepository extends JpaRepository<Layover, Integer> {
}
