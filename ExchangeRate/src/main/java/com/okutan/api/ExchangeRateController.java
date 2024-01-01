package com.okutan.api;

import com.okutan.model.ExchangeRateResponse;
import com.okutan.service.ExchangeRateService;
import com.okutan.util.ExchangeRateUtil;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exchangeRate")
public class ExchangeRateController {

    @Autowired
    ExchangeRateService exchangeRateService;

    @GetMapping("/{baseCurrency}/{targetCurrency}")
    @Retry(name = "exchange-rate", fallbackMethod = "exchangeRateFallback")
    public ResponseEntity<ExchangeRateResponse> getExchangeRate(@PathVariable("baseCurrency") String baseCurrency, @PathVariable("targetCurrency") String targetCurrency){
        ExchangeRateResponse response = new ExchangeRateResponse();
        String validationMessage = ExchangeRateUtil.validateField("sourceCurrency", baseCurrency);
        if(ExchangeRateUtil.isNullOrEmpty(validationMessage)){
            validationMessage = ExchangeRateUtil.validateField("targetCurrency", targetCurrency);
        }
        if(!ExchangeRateUtil.isNullOrEmpty(validationMessage)){
            response.setErrorMessage(validationMessage);
            return ResponseEntity.ok(response);
        }
        response = exchangeRateService.getExchangeRate(baseCurrency, targetCurrency);
        if(response == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<ExchangeRateResponse> exchangeRateFallback(Exception e){
        ExchangeRateResponse response = new ExchangeRateResponse();
        response.setErrorMessage("error in calling getExchangeRate : " + e.getMessage());
        return ResponseEntity.ok(response);
    }
}