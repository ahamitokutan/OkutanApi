package com.okutan.service;

import com.okutan.model.ExchangeRateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClientRequest;

import java.time.Duration;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService{

    @Autowired
    WebClient webClient;

    @Override
    public ExchangeRateResponse getExchangeRate(String baseCurrency, String targetCurrency) {
        ExchangeRateResponse response = webClient.get()
                .uri(uriBuilder -> uriBuilder.queryParam("base_currency", baseCurrency)
                        .queryParam("currencies", targetCurrency).build())
                .httpRequest(httpRequest -> {
                    HttpClientRequest reactorRequest = httpRequest.getNativeRequest();
                    reactorRequest.responseTimeout(Duration.ofSeconds(5));
                })
                .retrieve()
                .toEntity(ExchangeRateResponse.class)
                .block().getBody();
        return response;
    }
}
