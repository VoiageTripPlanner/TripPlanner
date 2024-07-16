package com.project.demo.entity.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>  {
    @Query("SELECT u FROM User u WHERE LOWER(u.name) LIKE %?1%")
    List<User> findUsersWithCharacterInName(String character);

    @Query("SELECT u FROM User u WHERE u.name = ?1")
    Optional<User> findByName(String name);

    @Query(value = "INSERT INTO \"user\" (created_at, email, lastname, name, password, role_id, updated_at) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7)", nativeQuery = true)
    void saveUser(Timestamp createdAt, String email, String lastname, String name, String password, Long roleId, Timestamp updatedAt);


    Optional<User> findByEmail(String email);
}
