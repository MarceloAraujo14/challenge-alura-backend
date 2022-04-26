package com.mbaraujo.analyst.transaction.mapper;

import com.mbaraujo.analyst.transaction.entity.TransactionModel;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class TransactionMapper {

    private final int ORIGIN_BANK = 0;
    private final int ORIGIN_AGENCY = 1;
    private final int ORIGIN_ACCOUNT = 2;
    private final int DESTINY_BANK = 3;
    private final int DESTINY_AGENCY = 4;
    private final int DESTINY_ACCOUNT = 5;
    private final int AMOUNT = 6;
    private final int DATE_TIME = 7;


    public TransactionModel toModel(String transaction){
        String[] fields = transaction.split(",");
        TransactionModel model = new TransactionModel(
                fields[ORIGIN_BANK],
                fields[ORIGIN_AGENCY],
                fields[ORIGIN_ACCOUNT],
                fields[DESTINY_BANK],
                fields[DESTINY_ACCOUNT],
                fields[DESTINY_AGENCY],
                new BigDecimal(fields[AMOUNT]),
                LocalDateTime.parse(fields[DATE_TIME])
        );

        return model;
    }

}
