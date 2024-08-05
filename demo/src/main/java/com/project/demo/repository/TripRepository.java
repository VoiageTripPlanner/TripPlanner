package com.project.demo.repository;

import com.project.demo.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface TripRepository extends JpaRepository<Trip, Integer> {
}
