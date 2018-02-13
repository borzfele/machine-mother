package com.borzfele.machinemother.util;

import com.borzfele.machinemother.dao.TransactionService;
import com.borzfele.machinemother.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Component
public class DBPopulator {



    private TransactionService transactionService;


    public DBPopulator(TransactionService transactionService) {
        this.transactionService = transactionService;
        Calendar date1 = new GregorianCalendar(2018,1, 19, 20, 12, 1);
        Calendar date2 = new GregorianCalendar(2018,1, 21, 20, 12, 1);
        Calendar date3 = new GregorianCalendar(2018,1, 22, 20, 12, 1);
        Calendar date4 = new GregorianCalendar(2018,1, 23, 20, 12, 1);
        Calendar date5 = new GregorianCalendar(2018,1, 24, 20, 12, 1);

        Transaction expense1 = new Transaction("pizza", -1490, "Marximban, jóság volt.");
        Transaction expense2 = new Transaction("banánsöröcs", -590, "Marximban, jóság volt.");
        Transaction expense3 = new Transaction("rózsa", -600, "random csőlakó nénitől");
        Transaction expense4 = new Transaction("bevásárlás", -12345, "listán látod");
        Transaction expense5 = new Transaction("bevásárlás", -3245, "listán látod");
        Transaction expense6 = new Transaction("bevásárlás", -4321, "listán látod");
        Transaction expense7 = new Transaction("bevásárlás", -567, "listán látod");

        Transaction income = new Transaction("pizza", 43000, "fizuka", true);

        expense1.setDate(date1);
        expense2.setDate(date1);
        expense3.setDate(date1);
        expense4.setDate(date2);
        expense5.setDate(date3);
        expense6.setDate(date4);
        expense7.setDate(date5);
        income.setDate(date1);

        transactionService.addTransaction(expense1);
        transactionService.addTransaction(expense2);
        transactionService.addTransaction(expense3);
        transactionService.addTransaction(expense4);
        transactionService.addTransaction(expense5);
        transactionService.addTransaction(expense6);
        transactionService.addTransaction(expense7);
        transactionService.addTransaction(income);
    }

}
