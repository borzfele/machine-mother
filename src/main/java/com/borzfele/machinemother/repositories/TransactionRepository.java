package com.borzfele.machinemother.repositories;

import com.borzfele.machinemother.models.Transaction;
import com.borzfele.machinemother.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

    List<Transaction> findAll();

    List<Transaction> findByYearAndMonthAndDay(int year, int month, int day);

    List<Transaction> findByYearAndMonth(int year, int month);

    List<Transaction> findAllByOwner(User owner);

    List<Transaction> findByYearAndMonthAndDayAndOwner(int year, int month, int day, User owner);

    List<Transaction> findByYearAndMonthAndOwner(int year, int month, User owner);
}
