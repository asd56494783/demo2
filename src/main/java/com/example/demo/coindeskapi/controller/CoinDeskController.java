package com.example.demo.coindeskapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/coindesk")
public class CoinDeskController {

    private final RestTemplate restTemplate;

    @Autowired
    public CoinDeskController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/test")
    public String test() {
        return "Test endpoint is working!";
    }

    @GetMapping("/currentprice")
    public ResponseEntity<String> getCurrentPrice() {
        String coindeskUrl = "https://api.coindesk.com/v1/bpi/currentprice.json";
        ResponseEntity<String> response = restTemplate.getForEntity(coindeskUrl, String.class);
        return response;
    }
}
