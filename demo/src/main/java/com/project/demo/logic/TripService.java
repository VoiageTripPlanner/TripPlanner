package com.project.demo.logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.demo.entity.*;
import com.project.demo.entity.request.OpenAIResponse;
import com.project.demo.entity.request.PaginationRequest;
import com.project.demo.logic.exceptions.TripServiceException;
import com.project.demo.logic.request.OpenAIService;
import com.project.demo.repository.CountryRepository;
import com.project.demo.repository.TripRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TripService implements IService<Trip, Integer> {
    private final TripRepository tripRepository;
    private final CurrencyService currencyService;
    private final UserService userService;
    private final CountryRepository countryRepository;
    private final OpenAIService openAIService;

    public TripService(TripRepository tripRepository, CurrencyService currencyService, UserService userService, CountryRepository countryRepository, OpenAIService openAIService) {
        this.tripRepository = tripRepository;
        this.currencyService = currencyService;
        this.userService = userService;
        this.countryRepository = countryRepository;
        this.openAIService = openAIService;
    }

    @Override
    @Transactional
    public Trip save(Trip entity) {
        try {
            Currency curr = currencyService.findByIdTrip(entity.getCurrency().getCurrencyId());
            entity.setCurrency(curr);
            entity.setUser(userService.findByIdTrip(entity.getUser().getUser_id()));

            entity.getFlight().getLayovers().forEach(layover -> {
                layover.setParentFlight(entity.getFlight());
            });

            entity.getActivities().forEach(activity -> activity.setImageUrl("test"));
            entity.getLodge().setImages("testlodge");
            entity.getFlight().setTrip(entity);

            entity.getRestaurants().forEach(restaurant -> restaurant.setTrip(entity));
            entity.getActivities().forEach(activity -> activity.setTrip(entity));

            if (entity.getDestinationCountry() != null) {
                String countryName = extractCountryNameFromDestination(entity.getDestinationCountry().getCountryName());
                Country country = countryRepository.findByCountryName(countryName).orElse(null);
                if (country != null) {
                    entity.setDestinationCountry(country);
                } else {
                    entity.getDestinationCountry().setCurrency(curr);
                }
            }
            return tripRepository.save(entity);

        } catch (IllegalArgumentException e) {
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
            return tripRepository.findAllOperational();
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

    public PaginationRequest<List<Trip>> findAllByPage(Integer userId, Integer page, Integer size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Trip> pageTrip = tripRepository.findByUserIdPage(userId, pageable);
            return new PaginationRequest<List<Trip>>(pageTrip.getNumber(), pageTrip.getSize(), (int) pageTrip.getTotalElements(), pageTrip.getTotalPages(), pageTrip.getContent());
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
            Trip trip = tripRepository.findByIdAndOperationalTrue(integer);
            Objects.requireNonNull(trip, "Trip not found.");
            return trip;
        } catch (NullPointerException e) {
            throw new TripServiceException(
                    "Trip not found with id " + integer,
                    HttpStatus.NOT_FOUND,
                    "TRIP_NOT_FOUND",
                    "The trip with the given ID was not found.",
                    e
            );
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

    public Trip updateTrip(Integer id, Trip trip) {
        try {
            Optional<Trip> optionalTrip = tripRepository.findById(id);
            if (optionalTrip.isPresent()) {
                Trip existingTrip = optionalTrip.get();
                existingTrip.setName(trip.getName());
                existingTrip.setDescription(trip.getDescription());
                existingTrip.setDepartureDate(trip.getDepartureDate());
                existingTrip.setReturnDate(trip.getReturnDate());
                existingTrip.setBudget(trip.getBudget());
                return tripRepository.save(existingTrip);
            } else {
                throw new TripServiceException(
                        HttpStatus.NOT_FOUND,
                        "TRIP_NOT_FOUND",
                        "The trip with the given ID was not found.",
                        java.time.Instant.now().toString()

                );
            }
        } catch (Exception e) {
            throw new TripServiceException(
                    "Failed to update trip.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "UPDATE_ERROR",
                    "An error occurred while updating the trip.",
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
            tripRepository.setOperationalFalseById(integer);
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

    public List<Trip> findByUserId(Integer id) {
        try {
            return tripRepository.findByUserId(id);
        } catch (Exception e) {
            throw new TripServiceException(
                    "Failed to find trips by user ID.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "REPOSITORY_ERROR",
                    "An error occurred while deleting the trip. Please try again later.",
                    e
            );
        }
    }
    private String extractCountryNameFromDestination(String destinationCountryName) {
        try {
            String query = String.format("{\"destination\": \"%s\"}", destinationCountryName);
            String prompt = "You are an expert in geography. Extract the country name from the following text and format it with the first letter of each word in caps. Return only the country name.\n" +
                    "Example: 'Barcelona, Spain' should return 'Spain'.\n" +
                    "Text: " + destinationCountryName;

            String openAIResponseString = openAIService.generateTravelSuggestions(query, prompt, null);
            ObjectMapper objectMapper = new ObjectMapper();
            OpenAIResponse openAIResponse = objectMapper.readValue(openAIResponseString, OpenAIResponse.class);

            String content = openAIResponse.getChoices().get(0).getMessage().getContent();
            content = content.replace("```json", "").replace("```", "").trim();

            return content;
        } catch (Exception e) {
            throw new TripServiceException(
                    "Failed to extract country name from destination.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "OPENAI_SERVICE_ERROR",
                    "An error occurred while extracting the country name from the destination.",
                    e
            );
        }
    }
}
