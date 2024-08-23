package com.project.demo.repository;

import com.project.demo.entity.Trip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Integer> {

    @Query("SELECT t FROM Trip t WHERE t.user.user_id = :userId AND t.operational = true")
    List<Trip> findByUserId(@Param("userId") Integer userId);

    @Query("SELECT t FROM Trip t WHERE t.user.user_id = :userId AND t.operational = true")
    Page<Trip> findByUserIdPage(@Param("userId") Integer userId, Pageable pageable);

    @Query("SELECT t FROM Trip t WHERE t.id = :id AND t.operational = true")
    Trip findByIdAndOperationalTrue(@Param("id") Integer id);

    @Query("SELECT t FROM Trip t WHERE t.operational = true")
    List<Trip> findAllOperational();

    @Query("SELECT t.destinationCountry.countryId, t.destinationCountry.countryName, COUNT(t) as tripCount FROM Trip t WHERE t.operational = true AND t.user.user_id = :userId GROUP BY t.destinationCountry.countryId, t.destinationCountry.countryName ORDER BY tripCount DESC")
    List<Object[]> findTripCountByUserAndCountry(@Param("userId") Integer userId);

    @Transactional
    @Modifying
    @Query("UPDATE Trip t SET t.operational = false WHERE t.id = :id")
    void setOperationalFalseById(@Param("id") Integer id);
}
