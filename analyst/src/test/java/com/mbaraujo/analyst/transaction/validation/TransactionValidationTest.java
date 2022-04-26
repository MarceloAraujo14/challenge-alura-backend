package com.mbaraujo.analyst.transaction.validation;

import com.mbaraujo.analyst.transaction.entity.TransactionModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = {TransactionValidation.class})
@ExtendWith(MockitoExtension.class)
class TransactionValidationTest {

    @InjectMocks
    private TransactionValidation validation;

    private TransactionModel validT1;
    private TransactionModel validT2;
    private TransactionModel validT3;
    private TransactionModel invalidT4;
    private TransactionModel invalidT5;

    @BeforeEach
    void setUp() {
        this.validT1 = new TransactionModel(
                "TRANSACTION1",
                "0001",
                "00001-1",
                "BANCO BRADESCO",
                "0001",
                "00001-1",
                new BigDecimal("8000"),
                LocalDateTime.parse("2022-01-01T07:30:00")
        );
        this.validT2 = new TransactionModel(
                "TRANSACTION2",
                "0001",
                "00001-1",
                "BANCO BRADESCO",
                "0001",
                "00001-1",
                new BigDecimal("8000"),
                LocalDateTime.parse("2022-01-01T07:30:00")
        );
        this.validT3 = new TransactionModel(
                "TRANSACTION3",
                "0001",
                "00001-1",
                "BANCO BRADESCO",
                "0001",
                "00001-1",
                new BigDecimal("8000"),
                LocalDateTime.parse("2022-01-01T07:30:00")
        );
        this.invalidT4 = new TransactionModel(
                "TRANSACTION4",
                "0001",
                "00001-1",
                "BANCO BRADESCO",
                "0001",
                "00001-1",
                new BigDecimal("8000"),
                LocalDateTime.parse("2022-01-02T07:30:00")
        );

        this.invalidT5 = new TransactionModel(
                "TRANSACTION5",
                "",
                "00001-1",
                "BANCO BRADESCO",
                "0001",
                "00001-1",
                new BigDecimal("8000"),
                LocalDateTime.parse("2022-01-02T07:30:00")
        );



    }


    @DisplayName("Should return all transactions as valids.")
    @Test
    void validTransactions() {

        //given
        List<TransactionModel> list = List.of(validT1, validT2, validT3);
        //when
        List<TransactionModel> validTransactions = this.validation.validTransactions(list);
        //then
        assertThat(validTransactions).isEqualTo(list);

    }

    @DisplayName("Should not return transaction with date different from first valid transaction.")
    @Test
    void validTransactions2() {

        //given
        List<TransactionModel> list = List.of(validT1, validT2, validT3, invalidT4);
        //when
        List<TransactionModel> result = this.validation.validTransactions(list);
        List<TransactionModel> expect = List.of(validT1, validT2, validT3);
        //then
        assertThat(result).isEqualTo(expect);

    }

    @DisplayName("Should not return transaction with empity field.")
    @Test
    void validTransactions3() {

        //given
        List<TransactionModel> list = List.of(validT1, validT2, validT3, invalidT4, invalidT5);
        //when
        List<TransactionModel> result = this.validation.validTransactions(list);
        List<TransactionModel> expect = List.of(validT1, validT2, validT3);
        //then
        assertThat(result).isEqualTo(expect);

    }

    @DisplayName("Should not save the first transaction date if the transactions is invalid.")
    @Test
    void validTransactions4() {

        //given
        List<TransactionModel> list = List.of(invalidT5, validT1, validT2, validT3, invalidT4);
        //when
        List<TransactionModel> result = this.validation.validTransactions(list);
        List<TransactionModel> expect = List.of(validT1, validT2, validT3);
        //then
        assertThat(result).isEqualTo(expect);

    }


}