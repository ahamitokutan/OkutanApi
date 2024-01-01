import com.okutan.model.CurrencyConversionTransaction;
import com.okutan.repository.CurrencyConversionTransactionRepository;
import com.okutan.service.ConversionServiceImpl;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {ConversionServiceTest.class})
public class ConversionServiceTest {


    @InjectMocks
    ConversionServiceImpl conversionService;

    @Mock
    CurrencyConversionTransactionRepository currencyConversionTransactionRepository;

    @Test
    @Order(1)
    public void test_conversion_list_by_transaction_date(){
        List<CurrencyConversionTransaction> transactionList = new ArrayList<>();
        transactionList.add(CurrencyConversionTransaction.build(null, new Date(), "2024-01-02", "USD", "TRY", 100d, 200d, 2d));
        when(currencyConversionTransactionRepository.findByTransactionDate(1,11, "2024-01-02"))
                .thenReturn(transactionList);
        assertEquals(1, transactionList.size());
    }

}
