package com.example.demo.coindeskapi.repository;

import com.example.demo.coindeskapi.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Optional<Currency> findByCurrencyCode(String currencyCode);
}