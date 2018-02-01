package com.borzfele.machinemother.dao;

import com.borzfele.machinemother.model.Transaction;
import com.borzfele.machinemother.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

        public long getSumOfIncome() {
            long sumOfIncome = 0;
            List<Transaction> transactions = getAll();

            if (transactions != null) {
                for (Transaction transaction : transactions) {
                    if (transaction.getValue() > 0) {
                        sumOfIncome += transaction.getValue();
                    }
                }
            }

            return sumOfIncome;
        }

        public long getSumOfIncomeByDay(int dayId) {
            long sumOfIncome = 0;
            List<Transaction> allTransactions = getAll();

            if (allTransactions != null) {
                for (Transaction transaction : allTransactions) {
                    if (transaction.getValue() > 0 && transaction.getDate().get(Calendar.DAY_OF_MONTH) == dayId) {
                        sumOfIncome += transaction.getValue();
                    }
                }
            }

            return sumOfIncome;
        }

        public long getSumOfExpensesByDay(int dayId) {
            long sumOfExpenses = 0;
            List<Transaction> allTransactions = getAll();

            if (allTransactions != null) {
                for (Transaction transaction : allTransactions) {
                    if (transaction.getValue() < 0 && transaction.getDate().get(Calendar.DAY_OF_MONTH) == dayId) {
                        sumOfExpenses += transaction.getValue();
                    }
                }
            }

            return sumOfExpenses;
        }

}
