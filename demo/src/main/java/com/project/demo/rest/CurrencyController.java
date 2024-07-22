package com.project.demo.rest;

import com.project.demo.entity.Currency;
import com.project.demo.logic.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/currency")
public class CurrencyController implements IController<Currency, Integer>{
    @Autowired
    private CurrencyService currencyService;

    @Override
    public Currency create(Currency entity) {
        return currencyService.save(entity);
    }

    @Override
    public List<Currency> retrieveAll() {
        return currencyService.findAll();
    }

    @Override
    public Optional<Currency> retrieveById(Integer integer) {
        return currencyService.findById(integer);
    }

    @Override
    public Currency update(Currency entity) {
        return currencyService.update(entity);
    }

    @Override
    public void deleteById(Integer integer) {
        currencyService.deleteById(integer);
    }
}
