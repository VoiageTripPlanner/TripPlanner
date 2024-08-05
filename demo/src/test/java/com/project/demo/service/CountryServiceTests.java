package com.project.demo.service;

import com.project.demo.entity.Country;
import com.project.demo.logic.CountryService;
import com.project.demo.repository.CountryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CountryServiceTests {
    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private CountryService countryService;

    @Test
    public void CountryService_FindAll_ReturnsAllCountries() {
        List<Country> countryList = Mockito.mock(List.class);
        Mockito.when(countryRepository.findAll()).thenReturn(countryList);

        List<Country> countryListResponse = countryService.findAll();

        Assertions.assertThat(countryListResponse).isNotNull();
    }
}
