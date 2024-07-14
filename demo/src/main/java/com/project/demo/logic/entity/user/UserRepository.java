package com.project.demo.logic.entity.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>  {
    @Query("SELECT u FROM User u WHERE LOWER(u.name) LIKE %?1%")
    List<User> findUsersWithCharacterInName(String character);

    @Query("SELECT u FROM User u WHERE u.name = ?1")
    Optional<User> findByName(String name);

    @Query("SELECT u FROM User u WHERE LOWER(u.email) LIKE %?1%")
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE LOWER(u.otp) LIKE %?1%")
    Optional<User> findByOTP(String otp);
}


