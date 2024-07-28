package com.project.demo.rest;

import com.project.demo.entity.Currency;
import com.project.demo.logic.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/currency")
public class CurrencyController implements IController<Currency, Integer>{
    @Autowired
    private CurrencyService currencyService;

    @Override
    @PostMapping
    public Currency create(@RequestBody Currency entity) {
        return currencyService.save(entity);
    }

    @Override
    @GetMapping
    public List<Currency> retrieveAll() {
        return currencyService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public Optional<Currency> retrieveById(@PathVariable Integer integer) {
        return currencyService.findById(integer);
    }

    @Override
    @PutMapping
    public Currency update(@RequestBody Currency entity) {
        return currencyService.update(entity);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer integer) {
        currencyService.deleteById(integer);
    }

}
