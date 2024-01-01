package com.okutan.repository;

import com.okutan.model.CurrencyConversionTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CurrencyConversionTransactionRepository extends JpaRepository<CurrencyConversionTransaction, Long> {

    @Query(value = "select * from " +
            "(SELECT ROW_NUMBER() OVER (ORDER BY TRANSACTION_DATE DESC) RN, * " +
            "    FROM CURRENCY_CONVERSION_TRANSACTION WHERE TRANSACTION_DAY = :transactionDay) " +
            "where RN >= :startRow and RN < :endRow " +
            "order by RN", nativeQuery = true)
    List<CurrencyConversionTransaction> findByTransactionDate(int startRow, int endRow, String transactionDay);

    List<CurrencyConversionTransaction> findByTransactionId(Long transactionId);

}
