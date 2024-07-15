package com.project.demo.rest;

import com.project.demo.entity.Country;
import com.project.demo.logic.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/country")
public class CountryController implements IController<Country, Integer>{
    @Autowired
    private CountryService countryService;

    @Override
    public Country create(Country entity) {
        return countryService.save(entity);
    }

    @Override
    public List<Country> retrieveAll() {
        return countryService.findAll();
    }

    @Override
    public Optional<Country> retrieveById(Integer integer) {
        return countryService.findById(integer);
    }

    @Override
    public Country update(Country entity) {
        return countryService.update(entity);
    }

    @Override
    public void deleteById(Integer integer) {
        countryService.deleteById(integer);
    }
}
