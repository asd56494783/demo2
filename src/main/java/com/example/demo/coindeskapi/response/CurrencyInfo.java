package com.example.demo.coindeskapi.response;

public class CurrencyInfo {
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrencyNameZh() {
        return currencyNameZh;
    }

    public void setCurrencyNameZh(String currencyNameZh) {
        this.currencyNameZh = currencyNameZh;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    private String currency;
    private String currencyNameZh;
    private float rate;

    public CurrencyInfo(String currency, String currencyNameZh, float rate) {
        this.currency = currency;
        this.currencyNameZh = currencyNameZh;
        this.rate = rate;
    }

    // Getters and setters
}
