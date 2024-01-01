package com.okutan.service;

import com.okutan.model.ConvertResponse;
import com.okutan.model.CurrencyConversionTransaction;

import java.util.Date;
import java.util.List;

public interface ConversionService {

    List<CurrencyConversionTransaction> getConversionListByTransactionDate(Date transactionDate, int pageNumber, int pageSize);

    List<CurrencyConversionTransaction> getConversionListByTransactionId(Long transactionId);

    ConvertResponse convertCurrency(String sourceCurrency, String targetCurrency, Double sourceAmount);
}
