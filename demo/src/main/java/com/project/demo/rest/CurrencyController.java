package com.project.demo.rest;

import com.project.demo.entity.Currency;
import com.project.demo.entity.request.CurrencyRequest;
import com.project.demo.logic.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/currency")
public class CurrencyController implements IController<CurrencyRequest, Integer>{
    @Autowired
    private CurrencyService currencyService;

    @Override
    public CurrencyRequest create(CurrencyRequest entity) {
        return currencyService.save(entity);
    }

    @Override
    @GetMapping
    public List<CurrencyRequest> retrieveAll() {
        return currencyService.findAll();
    }

    @Override
    @GetMapping({"/{id}"})
    public Optional<CurrencyRequest> retrieveById(@PathVariable Integer id) {
        return currencyService.findById(id);
    }

    @Override
    public CurrencyRequest update(CurrencyRequest entity) {
        return currencyService.update(entity);
    }

    @Override
    public void deleteById(Integer integer) {
        currencyService.deleteById(integer);
    }
}
