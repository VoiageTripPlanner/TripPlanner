package com.project.demo.logic;

import com.project.demo.entity.Country;
import com.project.demo.entity.User;
import com.project.demo.entity.UserCountryWishlist;
import com.project.demo.entity.request.CountryInterestRequest;
import com.project.demo.logic.exceptions.TripServiceException;
import com.project.demo.logic.exceptions.UserCountryWishlistServiceException;
import com.project.demo.repository.CountryRepository;
import com.project.demo.repository.UserCountryWishlistRepository;
import com.project.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserCountryWishlistService {
    @Autowired
    private UserCountryWishlistRepository userCountryWishlistRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private UserRepository userRepository;

    private User userEntity;

    public Optional<CountryInterestRequest> findById(Integer userId) {
        try {
            Optional<User> userEntity = userRepository.findById(userId);
            Optional<List<UserCountryWishlist>> userCountryWishlists = userCountryWishlistRepository.findUserCountryWishlistByUser(userId);
            List<String> countryList = userCountryWishlists.map(countryWishlists -> countryWishlists.stream()
                            .map(UserCountryWishlist::getCountry)
                            .map(country -> String.valueOf(country.getCountryId()))
                            .collect(Collectors.toList()))
                    .orElseGet(List::of);
            return userEntity.map(user -> new CountryInterestRequest(user.getUser_id(), countryList, List.of()));
        } catch (Exception e) {
            throw new TripServiceException(
                    "Failed to find a user country wishlist by User ID.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "REPOSITORY_ERROR",
                    "An error occurred while finding the user country wishlist. Please try again later.",
                    e
            );
        }
    }

    public Optional<CountryInterestRequest> updateEntries(CountryInterestRequest request) {
        try {
            userEntity = userRepository.findById(request.getUserId()).orElse(null);
            if (userEntity != null) {
                request.getSavedCountries().forEach(this::save);
                request.getDeletedCountries().forEach(this::deleteById);
            }
            return findById(request.getUserId());
        } catch (IllegalArgumentException e) {
            throw new UserCountryWishlistServiceException(
                    "Failed to save user country wishlist: invalid entity.",
                    HttpStatus.BAD_REQUEST,
                    "ILLEGAL_ARGUMENT_ERROR",
                    "The user country wishlist entity provided is invalid. Please provide a valid entity.",
                    e
            );
        } catch (OptimisticLockingFailureException e) {
            throw new UserCountryWishlistServiceException(
                    "Failed to save user country wishlist: optimistic locking failure.",
                    HttpStatus.CONFLICT,
                    "OPTIMISTIC_LOCKING_FAILURE",
                    "The user country wishlist entity has a version conflict. Please try again.",
                    e
            );
        } catch (Exception e) {
            throw new UserCountryWishlistServiceException(
                    "Failed to save user country wishlist: unexpected error.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "UNEXPECTED_ERROR",
                    "An unexpected error occurred while saving the user country wishlist. Please try again later.",
                    e
            );
        }
    }

    private void save(String countryId) {
        Optional<Country> countryEntity = countryRepository.findById(Integer.valueOf(countryId));
        if (countryEntity.isPresent()) {
            UserCountryWishlist userCountryWishlist = new UserCountryWishlist();
            userCountryWishlist.setUser(userEntity);
            userCountryWishlist.setCountry(countryEntity.get());
            userCountryWishlistRepository.save(userCountryWishlist);
        }
    }

    private void deleteById(String countryId) {
        Optional<UserCountryWishlist> userCountryWishlistEntity = userCountryWishlistRepository.findUserCountryWishlistByCountryAndUser(Integer.valueOf(countryId), userEntity.getUser_id());
        userCountryWishlistEntity.ifPresent(userCountryWishlist -> userCountryWishlistRepository.delete(userCountryWishlist));
    }
}
