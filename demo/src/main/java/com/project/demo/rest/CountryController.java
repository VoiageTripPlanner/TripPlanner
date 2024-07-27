package com.project.demo.rest;

import com.project.demo.entity.Country;
import com.project.demo.logic.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/country")
public class CountryController implements IController<Country, Integer>{
    @Autowired
    private CountryService countryService;

    @Override
    @PostMapping
    public Country create(@RequestBody Country entity) {
        return countryService.save(entity);
    }

    @Override
    @GetMapping
    public List<Country> retrieveAll() {
        return countryService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public Optional<Country> retrieveById(@PathVariable Integer integer) {
        return countryService.findById(integer);
    }

    @Override
    @PutMapping
    public Country update(@RequestBody Country entity) {
        return countryService.update(entity);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer integer) {
        countryService.deleteById(integer);
    }
}
