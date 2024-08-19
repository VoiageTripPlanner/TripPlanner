package com.project.demo.repository;

import com.project.demo.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Integer> {

    @Query("SELECT t FROM Trip t WHERE t.user.user_id = :userId")
    List<Trip> findByUserId(Integer userId);

}
