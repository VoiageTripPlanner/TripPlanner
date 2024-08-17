package com.project.demo.logic;

import com.project.demo.entity.*;
import com.project.demo.logic.exceptions.TripServiceException;
import com.project.demo.repository.TripRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripService implements IService<Trip, Integer>{
    private final TripRepository tripRepository;
    private final CurrencyService currencyService;
    private final UserService userService;

    public TripService(TripRepository tripRepository, CurrencyService currencyService, UserService userService) {
        this.tripRepository = tripRepository;
        this.currencyService = currencyService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public Trip save(Trip entity) {
        try{
            entity.setCurrency(currencyService.findByIdTrip(entity.getCurrency().getCurrencyId()));
            entity.setUser(userService.findByIdTrip(entity.getUser().getUser_id()));

            entity.getFlight().getLayovers().forEach(layover -> {
                    layover.setParentFlight(entity.getFlight());
                });

            entity.getFlight().setTrip(entity);

            entity.getRestaurants().forEach(restaurant -> restaurant.setTrip(entity));
            entity.getActivities().forEach(activity -> activity.setTrip(entity));

            return tripRepository.save(entity);
        }  catch (IllegalArgumentException e) {
            throw new TripServiceException(
                    "Failed to save trip: invalid entity.",
                    HttpStatus.BAD_REQUEST,
                    "ILLEGAL_ARGUMENT_ERROR",
                    "The trip entity provided is invalid. Please provide a valid entity.",
                    e
            );
        } catch (OptimisticLockingFailureException e) {
            throw new TripServiceException(
                    "Failed to save trip: optimistic locking failure.",
                    HttpStatus.CONFLICT,
                    "OPTIMISTIC_LOCKING_FAILURE",
                    "The trip entity has a version conflict. Please try again.",
                    e
            );
        } catch (Exception e) {
            throw new TripServiceException(
                    "Failed to save trip: unexpected error.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "UNEXPECTED_ERROR",
                    "An unexpected error occurred while saving the trip. Please try again later.",
                    e
            );
        }

    }

    @Override
    public List<Trip> findAll() {
        try {
            return tripRepository.findAll();
        } catch (Exception e) {
            throw new TripServiceException(
                    "Failed to retrieve all trips.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "REPOSITORY_ERROR",
                    "An error occurred while retrieving trips. Please try again later.",
                    e
            );
        }
    }

    @Override
    public Optional<Trip> findById(Integer integer) {
        return Optional.empty();
    }

    public Trip findByIdTrip(Integer integer) {
        try {
            return tripRepository.findById(integer).orElse(null);
        } catch (Exception e) {
            throw new TripServiceException(
                    "Failed to find trip by ID.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "REPOSITORY_ERROR",
                    "An error occurred while finding the trip. Please try again later.",
                    e
            );
        }
    }

    @Override
    public Trip update(Trip entity) {
        try {
            return tripRepository.save(entity);
        } catch (IllegalArgumentException e) {
            throw new TripServiceException(
                    "Failed to update trip: invalid entity.",
                    HttpStatus.BAD_REQUEST,
                    "ILLEGAL_ARGUMENT_ERROR",
                    "The trip entity provided is invalid. Please provide a valid entity.",
                    e
            );
        } catch (OptimisticLockingFailureException e) {
            throw new TripServiceException(
                    "Failed to update trip: optimistic locking failure.",
                    HttpStatus.CONFLICT,
                    "OPTIMISTIC_LOCKING_FAILURE",
                    "The trip entity has a version conflict. Please try again.",
                    e
            );
        } catch (Exception e) {
            throw new TripServiceException(
                    "Failed to update trip: unexpected error.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "UNEXPECTED_ERROR",
                    "An unexpected error occurred while updating the trip. Please try again later.",
                    e
            );
        }
    }

    @Override
    public void deleteById(Integer integer) {
        try {
            tripRepository.deleteById(integer);
        } catch (Exception e) {
            throw new TripServiceException(
                    "Failed to delete trip by ID.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "REPOSITORY_ERROR",
                    "An error occurred while deleting the trip. Please try again later.",
                    e
            );
        }
    }
}