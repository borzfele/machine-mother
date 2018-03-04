package com.borzfele.machinemother.repository;

import com.borzfele.machinemother.model.Transaction;
import com.borzfele.machinemother.model.User;
import com.borzfele.machinemother.services.UserService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

    List<Transaction> findAll();

    List<Transaction> findByYearAndMonthAndDay(int year, int month, int day);

    List<Transaction> findByYearAndMonth(int year, int month);

    List<Transaction> findAllByOwner(User owner);

    List<Transaction> findByYearAndMonthAndDayAndOwner(int year, int month, int day, User owner);

    List<Transaction> findByYearAndMonthAndOwner(int year, int month, User owner);
}
