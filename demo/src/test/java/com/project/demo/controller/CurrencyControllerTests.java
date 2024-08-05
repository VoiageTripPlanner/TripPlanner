package com.project.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.demo.entity.Currency;
import com.project.demo.logic.CurrencyService;
import com.project.demo.logic.JwtAuthenticationFilter;
import com.project.demo.logic.JwtService;
import com.project.demo.rest.CurrencyController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = CurrencyController.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtAuthenticationFilter.class))
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class CurrencyControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyService currencyService;

    @MockBean
    private JwtService jwtService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void CurrencyController_GetAllCurrencies_ReturnsCurrencyList() throws Exception {
        List<Currency> currencyList = new ArrayList<>();
        currencyList.add(new Currency());
        currencyList.add(new Currency());
        given(currencyService.findAll()).willReturn(currencyList);

        ResultActions response = mockMvc.perform(get("/currency")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(currencyList)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(currencyList)));
    }
}
