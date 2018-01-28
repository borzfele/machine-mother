package com.borzfele.machinemother.repository;

import com.borzfele.machinemother.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

    @Override
    Transaction findOne(Long transactionId);
}
