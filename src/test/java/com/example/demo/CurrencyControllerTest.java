package com.example.demo;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.example.demo.coindeskapi.controller.CurrencyController;
import com.example.demo.coindeskapi.model.Currency;
import com.example.demo.coindeskapi.repository.CurrencyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

@WebMvcTest(CurrencyController.class)
public class CurrencyControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private CurrencyRepository currencyRepository;

    @InjectMocks
    private CurrencyController currencyController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(currencyController).build();
    }

    @Test
    public void testGetAllCurrencies() throws Exception {
        Currency currency = new Currency();
        currency.setCurrencyCode("USD");
        currency.setCurrencyNameZh("美元");
        when(currencyRepository.findAll()).thenReturn(Arrays.asList(currency));

        mockMvc.perform(get("/api/currencies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].currencyCode").value("USD"))
                .andExpect(jsonPath("$[0].currencyNameZh").value("美元"));
    }

    @Test
    public void testGetCurrencyByCode() throws Exception {
        Currency currency = new Currency();
        currency.setCurrencyCode("USD");
        currency.setCurrencyNameZh("美元");
        when(currencyRepository.findByCurrencyCode(anyString())).thenReturn(Optional.of(currency));

        mockMvc.perform(get("/api/currencies/USD"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currencyCode").value("USD"))
                .andExpect(jsonPath("$.currencyNameZh").value("美元"));
    }

    @Test
    public void testCreateCurrency() throws Exception {
        Currency currency = new Currency();
        currency.setCurrencyCode("JPY");
        currency.setCurrencyNameZh("日圓");
        when(currencyRepository.save(any(Currency.class))).thenReturn(currency);

        mockMvc.perform(post("/api/currencies")
                        .contentType("application/json")
                        .content("{\"currencyCode\": \"JPY\", \"currencyNameZh\": \"日圓\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currencyCode").value("JPY"))
                .andExpect(jsonPath("$.currencyNameZh").value("日圓"));
    }

    @Test
    public void testUpdateCurrency() throws Exception {
        Currency existingCurrency = new Currency();
        existingCurrency.setCurrencyCode("JPY");
        existingCurrency.setCurrencyNameZh("日圓");

        Currency updatedCurrency = new Currency();
        updatedCurrency.setCurrencyCode("JPY");
        updatedCurrency.setCurrencyNameZh("更新日圓");

        when(currencyRepository.findById(anyLong())).thenReturn(Optional.of(existingCurrency));
        when(currencyRepository.save(any(Currency.class))).thenReturn(updatedCurrency);

        mockMvc.perform(put("/api/currencies/1")
                        .contentType("application/json")
                        .content("{\"currencyCode\": \"JPY\", \"currencyNameZh\": \"更新日圓\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currencyCode").value("JPY"))
                .andExpect(jsonPath("$.currencyNameZh").value("更新日圓"));
    }

    @Test
    public void testDeleteCurrency() throws Exception {
        Currency currency = new Currency();
        currency.setId(1L);
        currency.setCurrencyCode("JPY");
        currency.setCurrencyNameZh("日圓");

        when(currencyRepository.findById(1L)).thenReturn(Optional.of(currency));

        mockMvc.perform(delete("/api/currencies/1"))
                .andExpect(status().isOk());

        verify(currencyRepository, times(1)).delete(currency);
    }
}
