package com.borzfele.machinemother.logic;

import com.borzfele.machinemother.dao.TransactionService;
import com.borzfele.machinemother.repository.TransactionRepository;
import org.springframework.stereotype.Controller;

@Controller
public class TransactionController {

    private TransactionService transactionService;
    private TransactionRepository transactionRepository;

    public TransactionController(TransactionService transactionService, TransactionRepository transactionRepository) {
        this.transactionService = transactionService;
        this.transactionRepository = transactionRepository;
    }



}
