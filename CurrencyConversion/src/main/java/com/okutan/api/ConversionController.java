package com.okutan.api;

import com.okutan.model.ConversionListResponse;
import com.okutan.model.ConvertRequest;
import com.okutan.model.ConvertResponse;
import com.okutan.model.CurrencyConversionTransaction;
import com.okutan.service.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class ConversionController {

    @Autowired
    ConversionService conversionService;

    @GetMapping("/conversions")
    public ResponseEntity<ConversionListResponse> getConversionList(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "transactionDate", required = false)
            @DateTimeFormat(pattern = "yyyy-mm-dd") Date transactionDate,
            @RequestParam(value = "transactionId", required = false) Long transactionId){
        ConversionListResponse response = new ConversionListResponse();
        if(transactionId != null){
            List<CurrencyConversionTransaction> conversionList = conversionService.getConversionListByTransactionId(transactionId);
            response.setTransactionList(conversionList);
        }
        else if(transactionDate != null){
            List<CurrencyConversionTransaction> conversionList = conversionService.getConversionListByTransactionDate(transactionDate, page, 10);
            response.setPageNumber(page);
            response.setTransactionList(conversionList);
        }
        else{
            response.setErrorMessage("transactionId or transactionDate must be sent in request");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/convert")
    public ResponseEntity<ConvertResponse> convert(@RequestBody ConvertRequest convertRequest){
        ConvertResponse response = conversionService.convertCurrency(convertRequest.getSourceCurrency(), convertRequest.getTargetCurrency(), convertRequest.getSourceAmount());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
