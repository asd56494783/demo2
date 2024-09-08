package com.example.demo.coindeskapi.controller;

import com.example.demo.coindeskapi.model.Currency;
import com.example.demo.coindeskapi.repository.CurrencyRepository;
import com.example.demo.coindeskapi.response.CoinDeskResponse;
import com.example.demo.coindeskapi.response.CoinDeskResponse.BpiData;
import com.example.demo.coindeskapi.response.CoinDeskResponse.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@WebMvcTest(TransformedCoinDeskController.class)
public class TransformedCoinDeskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private CurrencyRepository currencyRepository;

    private CoinDeskResponse mockCoinDeskResponse;

    @BeforeEach
    public void setup() {
        // 模擬CoinDesk API返回的數據
        mockCoinDeskResponse = new CoinDeskResponse();
        Map<String, BpiData> bpiMap = new HashMap<>();

        BpiData usd = new BpiData();
        usd.setCode("USD");
        usd.setRateFloat(54205.8491f);
        usd.setDescription("United States Dollar");
        bpiMap.put("USD", usd);

        BpiData gbp = new BpiData();
        gbp.setCode("GBP");
        gbp.setRateFloat(41279.2719f);
        gbp.setDescription("British Pound Sterling");
        bpiMap.put("GBP", gbp);

        mockCoinDeskResponse.setBpi(bpiMap);

        Time time = new Time();
        time.setUpdatedISO("2024-09-08T23:10:00+00:00");
        mockCoinDeskResponse.setTime(time);


        // 模擬RestTemplate調用外部API
        Mockito.when(restTemplate.getForObject(anyString(), Mockito.eq(CoinDeskResponse.class)))
                .thenReturn(mockCoinDeskResponse);


    }

    @Test
    public void testGetTransformedCoinDeskData() throws Exception {
        mockMvc.perform(get("/api/coindesk-transformed"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currencies[0].currency").value("GBP"))
                .andExpect(jsonPath("$.currencies[1].currency").value("USD"));
    }
}
