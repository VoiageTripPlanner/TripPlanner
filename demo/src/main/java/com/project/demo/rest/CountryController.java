package com.project.demo.rest;

import com.project.demo.entity.Country;
import com.project.demo.logic.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<Country> create(Country entity) {return ResponseEntity.ok(countryService.save(entity));}

    @Override
    @GetMapping
    public ResponseEntity<List<Country>> retrieveAll() {
        return ResponseEntity.ok(countryService.findAll());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Country> retrieveById(@PathVariable Integer id) {return ResponseEntity.ok(countryService.findById(id));}

    @Override
    public ResponseEntity<Country> update(Country entity) {return ResponseEntity.ok(countryService.update(entity));}

    @Override
    public ResponseEntity<Void> deleteById(Integer integer) {
        countryService.deleteById(integer);
        return ResponseEntity.ok().build();
    }
}
