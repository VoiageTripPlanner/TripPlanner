package com.project.demo.rest;

import com.project.demo.entity.request.CountryInterestRequest;
import com.project.demo.logic.UserCountryWishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/userCountryWishlist")
public class UserCountryWishlistController {
    @Autowired
    private UserCountryWishlistService userCountryWishlistService;


    @GetMapping("/{userId}")
    public ResponseEntity<CountryInterestRequest> getCountriesInterestByUser(@PathVariable Integer userId) {
        Optional<CountryInterestRequest> userCountryWishlist = userCountryWishlistService.findById(userId);
        return userCountryWishlist.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CountryInterestRequest> updateEntries(@RequestBody CountryInterestRequest request) {
        Optional<CountryInterestRequest> userCountryWishlist = userCountryWishlistService.updateEntries(request);
        return userCountryWishlist.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
