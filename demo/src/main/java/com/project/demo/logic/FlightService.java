package com.project.demo.logic;

import com.project.demo.entity.Flight;
import com.project.demo.logic.exceptions.FlightServiceException;
import com.project.demo.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService implements IService<Flight, Integer> {
    @Autowired
    private FlightRepository flightRepository;

    @Override
    public Flight save(Flight entity) {
        try {
            return flightRepository.save(entity);
        } catch (IllegalArgumentException e) {
            throw new FlightServiceException(
                    "Failed to save flight: entity is null.",
                    HttpStatus.BAD_REQUEST,
                    "ILLEGAL_ARGUMENT_ERROR",
                    "The flight entity provided is null. Please provide a valid entity.",
                    e
            );
        } catch (OptimisticLockingFailureException e) {
            throw new FlightServiceException(
                    "Failed to save flight: optimistic locking failure.",
                    HttpStatus.CONFLICT,
                    "OPTIMISTIC_LOCKING_ERROR",
                    "The flight entity has a version conflict. Please refresh and try again.",
                    e
            );
        } catch (Exception e) {
            throw new FlightServiceException(
                    "Failed to save flight.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "FLIGHT_SAVE_ERROR",
                    "An error occurred while saving the flight. Please try again later.",
                    e
            );
        }
    }

    @Override
    public List<Flight> findAll() {
        try {
            return flightRepository.findAll();
        } catch (Exception e) {
            throw new FlightServiceException(
                    "Failed to retrieve all flights.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "FLIGHT_RETRIEVE_ERROR",
                    "An error occurred while retrieving flights. Please try again later.",
                    e
            );
        }
    }

    @Override
    public Flight findById(Integer id) {
        try {
            return flightRepository.findById(id).orElse(null);
        } catch (IllegalArgumentException e) {
            throw new FlightServiceException(
                    "Failed to retrieve flight by ID: invalid ID.",
                    HttpStatus.BAD_REQUEST,
                    "ILLEGAL_ARGUMENT_ERROR",
                    "The flight ID provided is invalid. Please provide a valid ID.",
                    e
            );
        } catch (Exception e) {
            throw new FlightServiceException(
                    "Failed to retrieve flight by ID.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "FLIGHT_RETRIEVE_ERROR",
                    "An error occurred while retrieving the flight. Please try again later.",
                    e
            );
        }
    }

    @Override
    public Flight update(Flight entity) {
        try {
            return flightRepository.save(entity);
        } catch (IllegalArgumentException e) {
            throw new FlightServiceException(
                    "Failed to update flight: entity is null.",
                    HttpStatus.BAD_REQUEST,
                    "ILLEGAL_ARGUMENT_ERROR",
                    "The flight entity provided is null. Please provide a valid entity.",
                    e
            );
        } catch (OptimisticLockingFailureException e) {
            throw new FlightServiceException(
                    "Failed to update flight: optimistic locking failure.",
                    HttpStatus.CONFLICT,
                    "OPTIMISTIC_LOCKING_ERROR",
                    "The flight entity has a version conflict. Please refresh and try again.",
                    e
            );
        } catch (Exception e) {
            throw new FlightServiceException(
                    "Failed to update flight.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "FLIGHT_UPDATE_ERROR",
                    "An error occurred while updating the flight. Please try again later.",
                    e
            );
        }
    }

    @Override
    public void deleteById(Integer id) {
        try {
            flightRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new FlightServiceException(
                    "Failed to delete flight by ID: invalid ID.",
                    HttpStatus.BAD_REQUEST,
                    "ILLEGAL_ARGUMENT_ERROR",
                    "The flight ID provided is invalid. Please provide a valid ID.",
                    e
            );
        } catch (Exception e) {
            throw new FlightServiceException(
                    "Failed to delete flight by ID.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "FLIGHT_DELETE_ERROR",
                    "An error occurred while deleting the flight. Please try again later.",
                    e
            );
        }
    }

//    public void saveFlights(List<Flight> flights) {
//    }
}



