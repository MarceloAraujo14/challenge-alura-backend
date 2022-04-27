package com.mbaraujo.analyst.transaction.repository;

import com.mbaraujo.analyst.transaction.entity.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel, Long> {

    boolean existsByDate(LocalDate date);

}
