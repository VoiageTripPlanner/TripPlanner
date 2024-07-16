package com.project.demo.logic.entity.country;

import com.project.demo.logic.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long>  {
    @Query("SELECT u FROM Country u WHERE LOWER(u.country_name) LIKE %?1%")
    List<Country> findCountriesWithCharacterInName(String character);

    @Query("SELECT u FROM Country u WHERE u.country_id = :id")
    Optional<Country> findCountryById (@Param("id") Integer id);

    @Query("SELECT u FROM Country u WHERE u.country_name = ?1")
    Optional<Country> findByName(String name);

}