package com.example.demo.coindeskapi.controller;

import com.example.demo.coindeskapi.model.Currency;
import com.example.demo.coindeskapi.repository.CurrencyRepository;
import com.example.demo.coindeskapi.response.CoinDeskResponse;
import com.example.demo.coindeskapi.response.CurrencyInfo;
import com.example.demo.coindeskapi.response.TransformedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class TransformedCoinDeskController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CurrencyRepository currencyRepository;

    @GetMapping(value = "/coindesk-transformed", produces = "application/json")
    public ResponseEntity<?> getTransformedCoinDeskData() {
        // 呼叫 coindesk API
        String url = "https://api.coindesk.com/v1/bpi/currentprice.json";
        CoinDeskResponse response = restTemplate.getForObject(url, CoinDeskResponse.class);

        // 解析並轉換資料
        TransformedResponse transformedResponse = transformCoinDeskData(response);

        return ResponseEntity.ok(transformedResponse);
    }

    private TransformedResponse transformCoinDeskData(CoinDeskResponse response) {
        String updateTime = formatTime(response.getTime().getUpdatedISO());

        List<CurrencyInfo> currencyInfos = new ArrayList<>();
        response.getBpi().forEach((code, bpiData) -> {
            Optional<Currency> optionalCurrency = currencyRepository.findByCurrencyCode(code);
            String currencyName = optionalCurrency
                    .map(Currency::getCurrencyNameZh)
                    .orElse("Default Name");
            currencyInfos.add(new CurrencyInfo(code, currencyName, bpiData.getRateFloat()));
        });

        return new TransformedResponse(updateTime, currencyInfos);
    }


    private String formatTime(String isoTime) {
        // 將 ISO 時間轉換為 yyyy/MM/dd HH:mm:ss 格式
        DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime dateTime = LocalDateTime.parse(isoTime, isoFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return dateTime.format(outputFormatter);
    }
}

