package com.project.demo.logic;

import com.project.demo.entity.Location;
import com.project.demo.logic.exceptions.LocationServiceException;
import com.project.demo.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService implements IService<Location, Integer>{
    @Autowired
    private LocationRepository locationRepository;

    @Override
    public Location save(Location entity) {
        try {
            return locationRepository.save(entity);
        } catch (IllegalArgumentException e) {
            throw new LocationServiceException(
                    "Failed to save location: invalid entity.",
                    HttpStatus.BAD_REQUEST,
                    "ILLEGAL_ARGUMENT_ERROR",
                    "The location entity provided is invalid. Please provide a valid entity.",
                    e
            );
        } catch (OptimisticLockingFailureException e) {
            throw new LocationServiceException(
                    "Failed to save location: optimistic locking failure.",
                    HttpStatus.CONFLICT,
                    "OPTIMISTIC_LOCKING_FAILURE",
                    "The location entity has a version conflict. Please try again.",
                    e
            );
        } catch (Exception e) {
            throw new LocationServiceException(
                    "Failed to save location.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "LOCATION_SAVE_ERROR",
                    "An error occurred while saving the location. Please try again later.",
                    e
            );
        }
    }

    @Override
    public List<Location> findAll() {
        try {
            return locationRepository.findAll();
        } catch (Exception e) {
            throw new LocationServiceException(
                    "Failed to retrieve all locations.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "LOCATION_RETRIEVE_ERROR",
                    "An error occurred while retrieving locations. Please try again later.",
                    e
            );
        }
    }

    @Override
    public Location findById(Integer integer) {
        return null;
    }

    @Override
    public Location update(Location entity) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }
}
