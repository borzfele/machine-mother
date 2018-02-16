package com.borzfele.machinemother.dao;

import com.borzfele.machinemother.model.Transaction;
import com.borzfele.machinemother.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository TransactionRepository;

    public void saveTransaction(Transaction transaction) {
        TransactionRepository.save(transaction);
    }

    public List<Transaction> getAll() {
        return TransactionRepository.findAll();
    }

    public Transaction findById(Long id) {
        return TransactionRepository.findOne(id);
    }

    public void addTransaction(Transaction transaction) {
        if (transaction.getYear() != 0) {
            saveTransaction(transaction);
        } else {
            transaction.setYear(Calendar.getInstance().get(Calendar.YEAR));
            transaction.setMonth(Calendar.getInstance().get(Calendar.MONTH));
            transaction.setDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            saveTransaction(transaction);
        }
    }

    public long getSumOfIncome(List<Transaction> transactionList) {

        long sum = 0;

        for (Transaction transaction : transactionList) {
            if (transaction.getValue() > 0) {
                sum += transaction.getValue();
            }
        }

        return Math.abs(sum);
    }

    public long getSumOfExpenses(List<Transaction> transactionList) {

        long sum = 0;

        for (Transaction transaction : transactionList) {
            if (transaction.getValue() < 0) {
                sum += transaction.getValue();
            }
        }

        return Math.abs(sum);
    }

    public long getSumOf(List<Long> numberList) {
        long sum = 0;

        for (long num : numberList) {
            sum += num;
        }

        return sum;
    }

    public long getAvgOfExpenses(List<Transaction> transactionList) {
        return getSumOfExpenses(transactionList) / transactionList.size();
    }

    public long getAvgOfDailyExpenses(Calendar calendar) {
        List<Long> dailyAvgs = new ArrayList<>();

        for (int i = 1; i < calendar.getActualMaximum(Calendar.DAY_OF_MONTH) + 1; i++) {
            calendar.set(Calendar.DAY_OF_MONTH, i);
            if (findByYearAndMonthAndDay(calendar).size() != 0) {
                dailyAvgs.add(getAvgOfExpenses(findByYearAndMonthAndDay(calendar)));
            }
        }

        return getAvgOf(dailyAvgs);
    }

    public long getAvgOf(List<Long> numberList) {
        return getSumOf(numberList) / numberList.size();
    }

    public List<Transaction> findByYearAndMonthAndDay(Calendar calendar) {
        return TransactionRepository.findByYearAndMonthAndDay(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    public List<Transaction> findByYearAndMonth(Calendar calendar) {
        return TransactionRepository.findByYearAndMonth(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
    }


}
