package com.project.demo.rest;

import com.project.demo.entity.Currency;
import com.project.demo.logic.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/currency")
public class CurrencyController implements IController<Currency, Integer> {
    @Autowired
    private CurrencyService currencyService;

    @Override
    public ResponseEntity<Currency> create(Currency entity) {
        Currency createdCurrency = currencyService.save(entity);
        return ResponseEntity.ok(createdCurrency);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Currency>> retrieveAll() {
        List<Currency> currencies = currencyService.findAll();
        return ResponseEntity.ok(currencies);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Currency> retrieveById(@PathVariable Integer id) {
        Currency currency = currencyService.findById(id);
        return ResponseEntity.ok(currency);
    }

    @Override
    public ResponseEntity<Currency> update(Currency entity) {
        Currency updatedCurrency = currencyService.update(entity);
        return ResponseEntity.ok(updatedCurrency);
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer id) {
        currencyService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
