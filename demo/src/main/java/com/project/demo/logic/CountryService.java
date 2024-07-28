package com.project.demo.logic;

import com.project.demo.entity.Country;
import com.project.demo.entity.request.CountryRequest;
import com.project.demo.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryService implements IService<CountryRequest, Integer>{
    @Autowired
    private CountryRepository countryRepository;

    @Override
    public CountryRequest save(CountryRequest entity) {
        return new CountryRequest();
    }

    @Override
    public List<CountryRequest> findAll() {
        List<Country> countries = countryRepository.findAll();
        return countries.stream()
                .map(country -> {
                    CountryRequest countryRequest = new CountryRequest();
                    countryRequest.setId(country.getCountryId().toString());
                    countryRequest.setName(country.getCountryName());
                    countryRequest.setCode(country.getCountryCode());
                    countryRequest.setCurrencyId(country.getCurrency().getCurrencyId().toString());
                    return countryRequest;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CountryRequest> findById(Integer integer) {
        return Optional.of(new CountryRequest());
    }

    @Override
    public CountryRequest update(CountryRequest entity) {
        return new CountryRequest();
    }

    @Override
    public void deleteById(Integer integer) {
        countryRepository.deleteById(integer);
    }
}
