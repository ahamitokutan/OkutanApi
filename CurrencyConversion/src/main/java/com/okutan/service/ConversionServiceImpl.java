package com.okutan.service;

import com.okutan.config.ExchangeRateClient;
import com.okutan.model.ConvertResponse;
import com.okutan.model.CurrencyConversionTransaction;
import com.okutan.model.ExchangeRateResponse;
import com.okutan.repository.CurrencyConversionTransactionRepository;
import com.okutan.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ConversionServiceImpl implements com.okutan.service.ConversionService {

    @Autowired
    CurrencyConversionTransactionRepository currencyConversionTransactionRepository;

    @Autowired
    ExchangeRateClient feignClient;

    @Override
    public List<CurrencyConversionTransaction> getConversionListByTransactionDate(Date transactionDate, int pageNumber, int pageSize) {
        int startRow = (pageNumber-1)*pageSize + 1;
        int endRow = pageNumber*pageSize + 1;
        return currencyConversionTransactionRepository.findByTransactionDate(startRow, endRow, DateUtil.getFormattedDate(transactionDate));
    }

    @Override
    public List<CurrencyConversionTransaction> getConversionListByTransactionId(Long transactionId) {
        return currencyConversionTransactionRepository.findByTransactionId(transactionId);
    }

    @Override
    public ConvertResponse convertCurrency(String sourceCurrency, String targetCurrency, Double sourceAmount) {
        ConvertResponse response = new ConvertResponse();
        ResponseEntity<ExchangeRateResponse> exchangeRateResponse = feignClient.getExchangeRate(sourceCurrency, targetCurrency);
        if(exchangeRateResponse.getStatusCode().equals(HttpStatus.OK)) {
            Double targetAmount, conversionRate;
            ExchangeRateResponse body = exchangeRateResponse.getBody();

            if(body != null && body.getData() != null && body.getData().containsKey(targetCurrency)){
                conversionRate = Double.valueOf(body.getData().get(targetCurrency));
                targetAmount = sourceAmount*conversionRate;
            }
            else if(body != null && body.getErrorMessage() != null){
                response.setErrorMessage("error calling exchangeRate service : " + body.getErrorMessage());
                return response;
            }
            else{
                response.setErrorMessage("target currency " + targetCurrency + " not found in ExchangeRate service");
                return response;
            }
            CurrencyConversionTransaction currencyConversionTransaction = new CurrencyConversionTransaction();
            currencyConversionTransaction.setSourceCurrency(sourceCurrency);
            currencyConversionTransaction.setTargetCurrency(targetCurrency);
            currencyConversionTransaction.setSourceAmount(sourceAmount);
            currencyConversionTransaction.setTargetAmount(targetAmount);
            currencyConversionTransaction.setConversionRate(conversionRate);
            currencyConversionTransaction.setTransactionDate(Calendar.getInstance().getTime());
            currencyConversionTransaction.setTransactionDay(DateUtil.getFormattedDate(currencyConversionTransaction.getTransactionDate()));
            currencyConversionTransaction = currencyConversionTransactionRepository.save(currencyConversionTransaction);
            response.setTargetAmount(currencyConversionTransaction.getTargetAmount());
            response.setTransactionId(currencyConversionTransaction.getTransactionId());
        }
        else{
            response.setErrorMessage("Error calling ExchangeRate Api" + exchangeRateResponse.getBody().getErrorMessage());
        }
        return response;
    }
}
