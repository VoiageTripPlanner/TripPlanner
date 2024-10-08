package com.project.demo.repository;

import com.project.demo.entity.Country;
import com.project.demo.entity.Role;
import com.project.demo.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>  {
    @Query("SELECT u FROM User u WHERE LOWER(u.name) LIKE %?1%")
    List<User> findUsersWithCharacterInName(String character);


    @Query("SELECT u FROM User u WHERE u.operational=true AND u.user_id <> :id")
    List<User> findUsersOperationalUsers(@Param("id") Integer id);
    @Query("SELECT u FROM User u WHERE u.name = ?1")
    Optional<User> findByName(String name);

    @Query(value = "INSERT INTO VO_User (name,last_name,second_last_name,country,email,password,operational,creation_datetime,creation_responsible,last_update_datetime,update_responsible) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7,?8,?9,?10,?11)", nativeQuery = true)
    void saveUser(String name, String last_name, String second_last_name, Country country, String email, String password, Boolean operational, Date creation_datetime, Integer creation_responsible, Date last_update_datetime, Integer update_responsible, Role role);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.operational = false WHERE u.user_id = :id")
    void logicalDeleteById(@Param("id") Integer id);

    @Query("SELECT u FROM User u")
    List<User> findAllUsers();


    @Query("SELECT u FROM User u JOIN FETCH u.country JOIN FETCH u.role")
    List<User> findUsersWithCountryAndRole();

    @Query("SELECT u FROM User u WHERE LOWER(u.email) LIKE %?1%")
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE LOWER(u.otp) LIKE %?1%")
    Optional<User> findByOTP(String otp);
}


