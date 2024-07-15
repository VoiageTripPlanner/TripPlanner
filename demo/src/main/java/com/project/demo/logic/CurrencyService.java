package com.project.demo.logic;

import com.project.demo.entity.Currency;
import com.project.demo.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService implements IService<Currency, Integer> {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public Currency save(Currency entity) {
        return currencyRepository.save(entity);
    }

    @Override
    public List<Currency> findAll() {
        return currencyRepository.findAll();
    }

    @Override
    public Optional<Currency> findById(Integer integer) {
        return currencyRepository.findById(integer);
    }

    @Override
    public Currency update(Currency entity) {
        return currencyRepository.save(entity);
    }

    @Override
    public void deleteById(Integer integer) {
        currencyRepository.deleteById(integer);
    }
}
