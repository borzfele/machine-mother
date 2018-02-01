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

        public List<Transaction> findByDate(Date date) {
            return TransactionRepository.findByDate(date);
        }

        public long getSumOfIncome() {
            long sumOfIncome = 0;
            List<Transaction> transactions = new ArrayList<Transaction>();
            transactions = getAll();

            for (Transaction transaction : transactions) {
                if (transaction.getValue() > 0) {
                    sumOfIncome += transaction.getValue();
                }
            }

            return sumOfIncome;
        }

        public long getSumOfIncomeByDay(Date date) {
            long sumOfIncome = 0;
            List<Transaction> incomesByDay = new ArrayList<Transaction>();
            incomesByDay = findByDate(date);

            for (Transaction transaction : incomesByDay) {
                if (transaction.getValue() > 0) {
                    sumOfIncome += transaction.getValue();
                }
            }

            return sumOfIncome;
        }

}
