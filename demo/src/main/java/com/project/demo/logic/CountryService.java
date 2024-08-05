package com.project.demo.logic;

import com.project.demo.entity.Country;

import com.project.demo.logic.exceptions.CountryServiceException;
import com.project.demo.repository.CountryRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CountryService implements IService<Country, Integer>{ // Needs refactor
    private final CountryRepository countryRepository;
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        try {
            return countryRepository.findAll();
        } catch (Exception e) {
            throw new CountryServiceException(
                    "Failed to retrieve all countries.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "REPOSITORY_ERROR",
                    "An error occurred while retrieving countries. Please try again later.",
                    e
            );
        }
    }

    @Override
    public void deleteById(Integer id) {
        try {
            if (!countryRepository.existsById(id)) {
                throw new CountryServiceException(
                        "Country with id " + id + " not found.",
                        HttpStatus.NOT_FOUND,
                        "RESOURCE_NOT_FOUND",
                        "The country with the specified ID does not exist.",
                        null
                );
            }
            countryRepository.deleteById(id);
        } catch (Exception e) {
            throw new CountryServiceException(
                    "Failed to delete country by ID.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "REPOSITORY_ERROR",
                    "An error occurred while deleting the country. Please try again later.",
                    e
            );
        }
    }


    // The following methods contain dummy implementations to satisfy the IService interface requirements.
    // These methods are not used in the actual application logic.
    @Override
    public Country save(Country entity) {return null;}
    @Override
    public Country findById(Integer integer) {return new Country();}

    @Override
    public Country update(Country entity) {return null;}

}
