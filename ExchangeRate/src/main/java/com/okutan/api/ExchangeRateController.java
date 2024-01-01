package com.okutan.api;

import com.okutan.model.ExchangeRateResponse;
import com.okutan.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeRateController {

    @Autowired
    ExchangeRateService exchangeRateService;

    @GetMapping("/exchangeRate/{baseCurrency}/{targetCurrency}")
    public ResponseEntity<ExchangeRateResponse> getExchangeRate(@PathVariable("baseCurrency") String baseCurrency, @PathVariable("targetCurrency") String targetCurrency){
        ExchangeRateResponse response = exchangeRateService.getExchangeRate(baseCurrency, targetCurrency);
        if(response == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(response);
    }
}