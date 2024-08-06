package com.project.demo.logic;

import com.project.demo.entity.*;
import com.project.demo.repository.TripRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService implements IService<Trip, Integer>{
    private final TripRepository tripRepository;
    private final CurrencyService currencyService;
    private final UserService userService;
    private final FlightService flightService;
    private final RestaurantService restaurantService;

    public TripService(TripRepository tripRepository, CurrencyService currencyService, UserService userService, FlightService flightService, RestaurantService restaurantService) {
        this.tripRepository = tripRepository;
        this.currencyService = currencyService;
        this.userService = userService;
        this.flightService = flightService;
        this.restaurantService = restaurantService;
    }

    @Override
    @Transactional
    public Trip save(Trip entity) {
        entity.setCurrency(currencyService.findById(entity.getCurrency().getCurrencyId()));
        entity.setUser(userService.findById(entity.getUser().getUser_id()));


        //entity.getFlights().replaceAll(flightService::save);
        entity.getRestaurants().replaceAll(restaurantService::save);

        //entity.getFlights().forEach(flight -> flight.setTrip(entity));
        entity.getRestaurants().forEach(restaurant -> restaurant.setTrip(entity));
        entity.getActivities().forEach(activity -> activity.setTrip(entity));

        return tripRepository.save(entity);
    }

    @Override
    public List<Trip> findAll() {
        return tripRepository.findAll();
    }

    @Override
    public Trip findById(Integer integer) {
        return null;
    }

    @Override
    public Trip update(Trip entity) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }
}
