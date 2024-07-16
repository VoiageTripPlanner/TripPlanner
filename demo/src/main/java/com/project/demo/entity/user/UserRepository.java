package com.project.demo.entity.user;

import com.project.demo.entity.rol.Role;
import com.project.demo.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>  {
    @Query("SELECT u FROM User u WHERE LOWER(u.name) LIKE %?1%")
    List<User> findUsersWithCharacterInName(String character);

    @Query("SELECT u FROM User u WHERE u.name = ?1")
    Optional<User> findByName(String name);

    @Query(value = "INSERT INTO VO_User (name,last_name,second_last_name,country,email,password,operational,creation_datetime,creation_responsible,last_update_datetime,update_responsible) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7,?8,?9,?10,?11)", nativeQuery = true)
    void saveUser(String name, String last_name, String second_last_name, Country country, String email, String password, Boolean operational, Date creation_datetime, Integer creation_responsible, Date last_update_datetime, Integer update_responsible, Role role);


    Optional<User> findByEmail(String email);
}


