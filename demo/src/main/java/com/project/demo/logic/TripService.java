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

    public TripService(TripRepository tripRepository, CurrencyService currencyService, UserService userService) {
        this.tripRepository = tripRepository;
        this.currencyService = currencyService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public Trip save(Trip entity) {
        entity.setCurrency(currencyService.findById(entity.getCurrency().getCurrencyId()));
        entity.setUser(userService.findByIdTrip(entity.getUser().getUser_id()));

        for (Flight flight : entity.getFlights()) {
            flight.setTrip(entity);
        }
        for (Restaurant restaurant : entity.getRestaurants()) {
            restaurant.setTrip(entity);
        }
        for (Activity activity : entity.getActivities()) {
            activity.setTrip(entity);
        }

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
