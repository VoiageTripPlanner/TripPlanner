package com.project.demo.repository;

import com.project.demo.entity.Country;
import com.project.demo.entity.Role;
import com.project.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>  {
    @Query("SELECT u FROM User u WHERE u.name = ?1")
    Optional<User> findByName(String name);

    @Query(value = "INSERT INTO VO_User (name,last_name,second_last_name,country,email,password,operational,creation_datetime,creation_responsible,last_update_datetime,update_responsible) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7,?8,?9,?10,?11)", nativeQuery = true)
    void saveUser(String name, String lastName, String secondLastName, Country country, String email, String password, Boolean operational, Date creationDatetime, Integer creationResponsible, Date lastUpdateDatetime, Integer updateResponsible, Role role);


    Optional<User> findByEmail(String email);
}
