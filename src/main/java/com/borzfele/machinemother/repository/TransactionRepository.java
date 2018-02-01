package com.borzfele.machinemother.repository;

import com.borzfele.machinemother.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

    @Override
    Transaction findOne(Long transactionId);

    List<Transaction> findByDate(Date transactionDate);
}
