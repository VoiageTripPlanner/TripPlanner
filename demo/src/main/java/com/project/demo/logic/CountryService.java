package com.project.demo.logic;

import com.project.demo.entity.Country;
import com.project.demo.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CountryService implements IService<Country, Integer>{
    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Country save(Country entity) {
        return countryRepository.save(entity);
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Integer integer) {
        return countryRepository.findById(integer);
    }

    @Override
    public Country update(Country entity) {
        return countryRepository.save(entity);
    }

    @Override
    public void deleteById(Integer integer) {
        countryRepository.deleteById(integer);
    }
}
