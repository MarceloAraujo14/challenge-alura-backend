package com.mbaraujo.analyst.transaction.service;

import com.mbaraujo.analyst.transaction.entity.TransactionModel;
import com.mbaraujo.analyst.transaction.entity.TransactionsRegister;
import com.mbaraujo.analyst.transaction.mapper.TransactionMapper;
import com.mbaraujo.analyst.transaction.repository.TransactionRegisterRepository;
import com.mbaraujo.analyst.transaction.repository.TransactionRepository;
import com.mbaraujo.analyst.transaction.validation.TransactionValidation;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
@AllArgsConstructor
@Log4j2
public class TransactionService {

    private final TransactionRegisterRepository registerRepository;
    private final TransactionRepository repository;
    @Autowired
    private TransactionMapper mapper;
    @Autowired
    private TransactionValidation validation;

    public void save(InputStream transactions) {

        List<TransactionModel> transacionList = new ArrayList<>();

        Scanner sc = new Scanner(transactions);
        sc.useDelimiter("\n").forEachRemaining(transaction -> transacionList.add(mapper.toModel(transaction)));

        List<TransactionModel> validList = validation.validTransactions(transacionList);
        if(registerRepository.existsByDate(validList.get(0).getDate().toLocalDate())){
            throw new IllegalStateException("Foi encontrado um registro de transações com essa data.");
        }
        repository.saveAll(validList);

        registerRepository.save(new TransactionsRegister(validList.get(0).getDate().toLocalDate(), LocalDateTime.now()));
    }
}
