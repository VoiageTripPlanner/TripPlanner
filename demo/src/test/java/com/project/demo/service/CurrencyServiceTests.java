package com.project.demo.service;

import com.project.demo.entity.Currency;
import com.project.demo.logic.CurrencyService;
import com.project.demo.repository.CurrencyRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CurrencyServiceTests {
    @Mock
    private CurrencyRepository currencyRepository;

    @InjectMocks
    private CurrencyService currencyService;

    @Test
    public void CurrencyService_FindAll_ReturnsAllCurrencies() {
        List<com.project.demo.entity.Currency> currencyList = Mockito.mock(List.class);

        Mockito.when(currencyRepository.findAll()).thenReturn(currencyList);

        List<Currency> currencyListResponse = currencyService.findAll();

        Assertions.assertThat(currencyListResponse).isNotNull();
    }
}
