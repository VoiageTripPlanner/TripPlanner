package com.project.demo.repository;

import com.project.demo.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    @Query("SELECT u FROM Country u WHERE u.countryName = ?1")
    Optional<Country> findByName(String name);
}
