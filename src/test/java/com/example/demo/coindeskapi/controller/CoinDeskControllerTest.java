package com.example.demo.coindeskapi.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CoinDeskController.class)
class CoinDeskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void testGetCurrentPrice() throws Exception {
        String mockResponse = "{\n" +
                "  \"time\": {\n" +
                "    \"updated\": \"Sep 08, 2024 21:53:38 UTC\",\n" +
                "    \"updatedISO\": \"2024-09-08T21:53:38Z\",\n" +
                "    \"updateduk\": \"Sep 08, 2024 at 22:53 BST\"\n" +
                "  },\n" +
                "  \"bpi\": {\n" +
                "    \"USD\": {\n" +
                "      \"code\": \"USD\",\n" +
                "      \"rate\": \"54,205.849\",\n" +
                "      \"description\": \"United States Dollar\",\n" +
                "      \"rate_float\": 54205.8491\n" +
                "    }\n" +
                "  }\n" +
                "}";


        when(restTemplate.getForEntity(Mockito.anyString(), Mockito.eq(String.class)))
                .thenReturn(new ResponseEntity<>(mockResponse, HttpStatus.OK));

        MvcResult result = mockMvc.perform(get("/api/coindesk/currentprice"))
                .andExpect(status().isOk())
                .andExpect(content().json(mockResponse))
                .andReturn();

        System.out.println("Response: " + result.getResponse().getContentAsString());
    }
}