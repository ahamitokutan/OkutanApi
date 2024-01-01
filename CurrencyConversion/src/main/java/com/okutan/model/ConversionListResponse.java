package com.okutan.model;

import lombok.Data;

import java.util.List;

@Data
public class ConversionListResponse extends com.okutan.model.BaseResponse {

    private List<CurrencyConversionTransaction> transactionList;
    private int pageNumber;
}
