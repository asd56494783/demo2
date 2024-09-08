package com.example.demo.coindeskapi.response;

import java.util.List;

public class TransformedResponse {
    private String updateTime;
    private List<CurrencyInfo> currencies;

    public TransformedResponse(String updateTime, List<CurrencyInfo> currencies) {
        this.updateTime = updateTime;
        this.currencies = currencies;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<CurrencyInfo> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<CurrencyInfo> currencies) {
        this.currencies = currencies;
    }
}

