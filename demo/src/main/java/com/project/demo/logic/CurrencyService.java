package com.project.demo.logic;

import com.project.demo.entity.Currency;
import com.project.demo.logic.exceptions.RepositoryException;
import com.project.demo.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService implements IService<Currency, Integer> {

    private final CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Currency save(Currency entity) {
        return new Currency();
    }

    @Override
    public List<Currency> findAll() {
        try {
            return currencyRepository.findAll();
        } catch (Exception e) {
            throw new RepositoryException(
                    "Failed to retrieve all currencies.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "REPOSITORY_ERROR",
                    "An error occurred while retrieving currencies. Please try again later.",
                    e
            );
        }
    }

    @Override
    public Currency findById(Integer integer) {
        return currencyRepository.findById(integer).orElse(null);
    }

    @Override
    public Currency update(Currency entity) {
        return new Currency();
    }

    @Override
    public void deleteById(Integer integer) {
        try {
            currencyRepository.deleteById(integer);
        } catch (Exception e) {
            throw new RepositoryException(
                    "Failed to delete currency by ID.",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "REPOSITORY_ERROR",
                    "An error occurred while deleting the currency. Please try again later.",
                    e
            );
        }
    }

    public com.project.demo.entity.Currency findByIdC(Integer integer){ //Revise this
        return currencyRepository.findById(integer).orElse(null);
    }
}
