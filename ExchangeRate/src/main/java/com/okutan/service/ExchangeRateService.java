package com.okutan.service;

import com.okutan.model.ExchangeRateResponse;

public interface ExchangeRateService {

    ExchangeRateResponse getExchangeRate (String baseCurrency, String targetCurrency);
}
