package com.okutan.util;

import java.util.Arrays;

public class ExchangeRateUtil {

    public static String[] currencyList = {"TRY", "USD", "EUR", "JPY", "BGN", "CZK", "DKK", "GBP", "HUF","PLN"
    , "RON", "SEK", "CHF", "ISK", "NOK", "HRK", "RUB", "AUD", "BRL", "CAD", "CNY", "HKD", "IDR", "ILS", "INR"
    , "KRW", "MXN", "MYR", "NZD", "PHP", "SGD", "THB", "ZAR"};

    public static String validateField(String fieldName, String fieldValue){
        if(isNullOrEmpty(fieldValue)){
            return fieldName + " cannot be empty";
        }
        if(!Arrays.asList(currencyList).contains(fieldValue)){
            return fieldValue + " not supported currency by Api";
        }
        return null;
    }

    public static boolean isNullOrEmpty(String s){
        return s == null || s.trim().length() == 0;
    }
}
