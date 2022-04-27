package com.mbaraujo.analyst.transaction.validation;

import com.mbaraujo.analyst.transaction.entity.TransactionModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Log4j2
public class TransactionValidation {

    private LocalDate transactionDate;

    public List<TransactionModel> validTransactions(List<TransactionModel> transactions){
        if(transactions.isEmpty()){
            throw new IllegalArgumentException("Não foram encontradas transações válidas no arquivo.");
        }
        transactionDate = transactions.get(0).getDateTime().toLocalDate();
        List<TransactionModel> validList = new ArrayList<>(transactions);
        transactions.forEach(transaction -> {
            if (!isValid(transaction)){
                log.info(transaction);
                validList.remove(transaction);
                transactionDate = validList.get(0).getDateTime().toLocalDate();
            }
        });

        return validList;
    }

    private boolean isValid(TransactionModel transaction){
        return !transaction.getAmount().toString().isEmpty()
                && !transaction.getDestinationAccount().isEmpty()
                && !transaction.getDestinationAgency().isEmpty()
                && !transaction.getDestinationBank().isEmpty()
                && !transaction.getOriginAccount().isEmpty()
                && !transaction.getOrignAgency().isEmpty()
                && !transaction.getOriginBank().isEmpty()
                && transaction.getDateTime().toLocalDate().isEqual(transactionDate);
    }


}
