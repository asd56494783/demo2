package com.example.demo.coindeskapi.controller;

import com.example.demo.coindeskapi.exception.ResourceNotFoundException;
import com.example.demo.coindeskapi.model.Currency;
import com.example.demo.coindeskapi.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyRepository currencyRepository;

    // 查詢所有幣別
    @GetMapping
    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    // 查詢單一幣別
    @GetMapping("/{code}")
    public Currency getCurrencyByCode(@PathVariable String code) {
        return currencyRepository.findByCurrencyCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Currency not found"));
    }

    // 新增幣別
    @PostMapping
    public Currency createCurrency(@RequestBody Currency currency) {
        return currencyRepository.save(currency);
    }

    // 修改幣別
    @PutMapping("/{id}")
    public Currency updateCurrency(@PathVariable Long id, @RequestBody Currency currencyDetails) {
        Currency currency = currencyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Currency not found"));

        currency.setCurrencyCode(currencyDetails.getCurrencyCode());
        currency.setCurrencyNameZh(currencyDetails.getCurrencyNameZh());

        return currencyRepository.save(currency);
    }

    // 刪除幣別
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCurrency(@PathVariable Long id) {
        Currency currency = currencyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Currency not found"));

        currencyRepository.delete(currency);

        return ResponseEntity.ok().build();
    }
}
