package com.project.demo.rest;

import com.project.demo.entity.Country;
import com.project.demo.entity.request.CountryRequest;
import com.project.demo.logic.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/country")
public class CountryController implements IController<CountryRequest, Integer>{
    @Autowired
    private CountryService countryService;

    @Override
    public CountryRequest create(CountryRequest entity) {
        return countryService.save(entity);
    }

    @Override
    @GetMapping
    public List<CountryRequest> retrieveAll() {
        return countryService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public Optional<CountryRequest> retrieveById(@PathVariable Integer id) {
        return countryService.findById(id);
    }

    @Override
    public CountryRequest update(CountryRequest entity) {
        return countryService.update(entity);
    }

    @Override
    public void deleteById(Integer integer) {
        countryService.deleteById(integer);
    }
}
