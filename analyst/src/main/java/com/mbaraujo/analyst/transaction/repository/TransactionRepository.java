package com.mbaraujo.analyst.transaction.repository;

import com.mbaraujo.analyst.transaction.entity.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel, Long> {
}
