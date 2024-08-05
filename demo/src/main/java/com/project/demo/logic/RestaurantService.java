package com.project.demo.logic;

import com.project.demo.entity.Restaurant;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService implements IService<Restaurant, Integer> {
    @Override
    public Restaurant save(Restaurant entity) {
        return null;
    }

    @Override
    public List<Restaurant> findAll() {
        return null;
    }

    @Override
    public Restaurant findById(Integer integer) {
        return null;
    }

    @Override
    public Restaurant update(Restaurant entity) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }

//    public void saveRestaurants(List<Restaurant> restaurants) {
//    }
}
