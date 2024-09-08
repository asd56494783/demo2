package com.example.demo.coindeskapi.response;

import java.util.Map;

public class CoinDeskResponse {
    private Map<String, BpiData> bpi;
    private Time time;
    private String disclaimer;
    private String chartName;

    public Map<String, BpiData> getBpi() {
        return bpi;
    }

    public void setBpi(Map<String, BpiData> bpi) {
        this.bpi = bpi;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public String getChartName() {
        return chartName;
    }

    public void setChartName(String chartName) {
        this.chartName = chartName;
    }

    public static class BpiData {
        private String code;
        private String symbol;
        private String rate;
        private String description;
        private float rateFloat;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public float getRateFloat() {
            return rateFloat;
        }

        public void setRateFloat(float rateFloat) {
            this.rateFloat = rateFloat;
        }
    }

    public static class Time {
        private String updatedISO;

        public String getUpdatedISO() {
            return updatedISO;
        }

        public void setUpdatedISO(String updatedISO) {
            this.updatedISO = updatedISO;
        }
    }
}

