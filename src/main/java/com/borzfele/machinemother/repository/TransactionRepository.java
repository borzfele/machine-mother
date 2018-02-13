package com.borzfele.machinemother.repository;

import com.borzfele.machinemother.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

    @Override
    Transaction findOne(Long transactionId);

    List<Transaction> findByYearAndMonthAndDay(int year, int month, int day);

    List<Transaction> findByYearAndMonth(int year, int month);
}
