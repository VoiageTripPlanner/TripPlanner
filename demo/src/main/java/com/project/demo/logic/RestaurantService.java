package com.project.demo.logic;

import com.project.demo.entity.Restaurant;
import com.project.demo.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService implements IService<Restaurant, Integer> {
    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant save(Restaurant entity) {
        return restaurantRepository.save(entity);
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
