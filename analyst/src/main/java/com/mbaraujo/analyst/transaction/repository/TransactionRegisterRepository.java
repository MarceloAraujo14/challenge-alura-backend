package com.mbaraujo.analyst.transaction.repository;

import com.mbaraujo.analyst.transaction.entity.TransactionsRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TransactionRegisterRepository extends JpaRepository<TransactionsRegister, Long> {
    boolean existsByDate(LocalDate transactionDate);
}
