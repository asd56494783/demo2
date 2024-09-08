package com.example.demo.coindeskapi.model;

import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;

@Entity
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String currencyCode;

    @Column(nullable = false)
    private String currencyNameZh;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyNameZh() {
        return currencyNameZh;
    }

    public void setCurrencyNameZh(String currencyNameZh) {
        this.currencyNameZh = currencyNameZh;
    }



}

