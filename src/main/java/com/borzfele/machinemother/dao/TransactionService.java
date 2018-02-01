package com.borzfele.machinemother.dao;

import com.borzfele.machinemother.model.Transaction;
import com.borzfele.machinemother.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.tree.TreeNode;
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

}
