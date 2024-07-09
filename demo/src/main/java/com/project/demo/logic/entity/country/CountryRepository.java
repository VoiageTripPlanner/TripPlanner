package com.project.demo.logic.entity.country;

import com.project.demo.logic.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long>  {
    @Query("SELECT u FROM Country u WHERE LOWER(u.country_name) LIKE %?1%")
    List<User> findUsersWithCharacterInName(String character);

    @Query("SELECT u FROM Country u WHERE u.country_name = ?1")
    Optional<User> findByName(String name);

}
