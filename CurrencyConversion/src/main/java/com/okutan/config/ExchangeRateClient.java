package com.okutan.config;

import com.okutan.model.ExchangeRateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "exchange-rate-service")
public interface ExchangeRateClient {

    @GetMapping("/exchangeRate/{baseCurrency}/{targetCurrency}")
    ResponseEntity<ExchangeRateResponse> getExchangeRate(@PathVariable("baseCurrency") String baseCurrency, @PathVariable("targetCurrency") String targetCurrency);
}
