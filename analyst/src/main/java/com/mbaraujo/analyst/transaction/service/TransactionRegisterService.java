package com.mbaraujo.analyst.transaction.service;

import com.mbaraujo.analyst.transaction.entity.TransactionsRegister;
import com.mbaraujo.analyst.transaction.repository.TransactionRegisterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class TransactionRegisterService {

    private final TransactionRegisterRepository repository;

    public List<TransactionsRegister> findAll(){
        return repository.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }

}
