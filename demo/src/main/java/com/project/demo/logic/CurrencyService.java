package com.project.demo.logic;

import com.project.demo.entity.Currency;
import com.project.demo.entity.request.CurrencyRequest;
import com.project.demo.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CurrencyService implements IService<CurrencyRequest, Integer> {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public CurrencyRequest save(CurrencyRequest entity) {
        return new CurrencyRequest();
    }

    @Override
    public List<CurrencyRequest> findAll() {
        List<Currency> currencyList = currencyRepository.findAll();
        return currencyList.stream()
                .map(currency -> {
                    CurrencyRequest currencyRequest = new CurrencyRequest();
                    currencyRequest.setId(currency.getCurrencyId().toString());
                    currencyRequest.setName(currency.getCurrencyName());
                    currencyRequest.setCode(currency.getCurrencyCode());
                    currencyRequest.setCurrencySymbol(currency.getCurrencySymbol());
                    return currencyRequest;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CurrencyRequest> findById(Integer integer) {
        return Optional.of(new CurrencyRequest());
    }

    @Override
    public CurrencyRequest update(CurrencyRequest entity) {
        return new CurrencyRequest();
    }

    @Override
    public void deleteById(Integer integer) {
        currencyRepository.deleteById(integer);
    }
}
