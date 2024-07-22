package com.project.demo.repository;

import com.project.demo.entity.Lodge;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface LodgeRepository extends JpaRepository<Lodge, Integer> {
    @Query("SELECT u FROM Lodge u WHERE LOWER(u.lodgeName) LIKE %?1%")
    List<Lodge> findLodgeWithCharacterInName(String character);

    @Query("SELECT u FROM Lodge u WHERE u.lodgeName = ?1")
    Optional<Lodge> findByLodgeName(String name);

    @Modifying
    @Transactional
    @Query("UPDATE Lodge u SET u.operational = false WHERE u.lodgeId = :id")
    void logicalDeleteById(@Param("id") Integer id);

    @Query("SELECT u FROM Lodge u")
    List<Lodge> findAllUsers();


}
