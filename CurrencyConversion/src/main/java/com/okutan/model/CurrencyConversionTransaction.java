package com.okutan.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class CurrencyConversionTransaction {

    @Id
    @GeneratedValue
    private Long transactionId;

    private Date transactionDate;
    private String transactionDay;
    private String sourceCurrency;
    private String targetCurrency;
    private Double sourceAmount;
    private Double targetAmount;
    private Double conversionRate;

}
