package com.project.demo.repository;

import com.project.demo.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

    @Query("SELECT u FROM Country u WHERE u.countryName = ?1")
    Optional<Country> findByCountryName(String countryName);

    @Query("SELECT u FROM Country u WHERE LOWER(u.countryName) LIKE %?1%")
    List<Country> findCountriesWithCharacterInName(String character);


}