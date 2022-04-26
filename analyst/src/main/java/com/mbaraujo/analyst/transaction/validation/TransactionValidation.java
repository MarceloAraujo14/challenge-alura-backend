package com.mbaraujo.analyst.transaction.validation;

import com.mbaraujo.analyst.transaction.entity.TransactionModel;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class TransactionValidation {

    private LocalDateTime transactionDate;

    @Bean
    public List<TransactionModel> validTransactions(List<TransactionModel> transactions){
        if(transactions.isEmpty()){
            throw new IllegalArgumentException("Não foram encontradas transações válidas no arquivo.");
        }
        this.transactionDate = transactions.get(0).getDateTime();
        List<TransactionModel> validList = new ArrayList<>(transactions);
        transactions.forEach(transaction -> {
            if (!isValid(transaction)){
                validList.remove(transaction);
                this.transactionDate = validList.get(0).getDateTime();
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
                && transaction.getDateTime().isEqual(transactionDate);
    }


}
