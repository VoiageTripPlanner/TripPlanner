package com.project.demo.repository;

import com.project.demo.entity.Country;
import com.project.demo.entity.UserCountryWishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserCountryWishlistRepository extends JpaRepository<UserCountryWishlist, Integer> {
    @Query("SELECT u FROM UserCountryWishlist u WHERE u.country.countryId = :countryId AND u.user.user_id = :userId")
    Optional<UserCountryWishlist> findUserCountryWishlistByCountryAndUser(@Param("countryId") Integer countryId, @Param("userId") Integer userId);

    @Query("SELECT u FROM UserCountryWishlist u WHERE u.user.user_id = :userId")
    Optional<List<UserCountryWishlist>> findUserCountryWishlistByUser(@Param("userId") Integer userId);
}
