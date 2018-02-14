package com.borzfele.machinemother.util;

import com.borzfele.machinemother.dao.TransactionService;
import com.borzfele.machinemother.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Component
public class DBPopulator {



    private TransactionService transactionService;


    public DBPopulator(TransactionService transactionService) {
        this.transactionService = transactionService;
        Calendar date1 = new GregorianCalendar(2018,0, 19, 20, 12, 1);
        Calendar date2 = new GregorianCalendar(2018,0, 21, 20, 12, 1);
        Calendar date3 = new GregorianCalendar(2018,0, 22, 20, 12, 1);
        Calendar date4 = new GregorianCalendar(2018,0, 23, 20, 12, 1);
        Calendar date5 = new GregorianCalendar(2018,0, 24, 20, 12, 1);

        Transaction expense1 = new Transaction("pizza", -1490, "Marximban, jóság volt.");
        Transaction expense2 = new Transaction("banánsöröcs", -590, "Marximban, jóság volt.");
        Transaction expense3 = new Transaction("rózsa", -600, "random csőlakó nénitől");
        Transaction expense4 = new Transaction("bevásárlás", -12345, "listán látod");
        Transaction expense5 = new Transaction("bevásárlás", -3245, "listán látod");
        Transaction expense6 = new Transaction("bevásárlás", -4321, "listán látod");
        Transaction expense7 = new Transaction("bevásárlás", -567, "listán látod");

        Transaction income = new Transaction("pizza", 43000, "fizuka", true);

        expense1.setYear(date1.get(Calendar.YEAR));
        expense1.setMonth(date1.get(Calendar.MONTH));
        expense1.setDay(date1.get(Calendar.DAY_OF_MONTH));
        expense2.setYear(date1.get(Calendar.YEAR));
        expense2.setMonth(date1.get(Calendar.MONTH));
        expense2.setDay(date1.get(Calendar.DAY_OF_MONTH));
        expense3.setYear(date1.get(Calendar.YEAR));
        expense3.setMonth(date1.get(Calendar.MONTH));
        expense3.setDay(date1.get(Calendar.DAY_OF_MONTH));
        expense4.setYear(date2.get(Calendar.YEAR));
        expense4.setMonth(date2.get(Calendar.MONTH));
        expense4.setDay(date2.get(Calendar.DAY_OF_MONTH));
        expense5.setYear(date3.get(Calendar.YEAR));
        expense5.setMonth(date3.get(Calendar.MONTH));
        expense5.setDay(date3.get(Calendar.DAY_OF_MONTH));
        expense6.setYear(date4.get(Calendar.YEAR));
        expense6.setMonth(date4.get(Calendar.MONTH));
        expense6.setDay(date4.get(Calendar.DAY_OF_MONTH));
        expense7.setYear(date5.get(Calendar.YEAR));
        expense7.setMonth(date5.get(Calendar.MONTH));
        expense7.setDay(date5.get(Calendar.DAY_OF_MONTH));
        income.setYear(date1.get(Calendar.YEAR));
        income.setMonth(date1.get(Calendar.MONTH));
        income.setDay(date1.get(Calendar.DAY_OF_MONTH));

        transactionService.saveTransaction(expense1);
        transactionService.saveTransaction(expense2);
        transactionService.saveTransaction(expense3);
        transactionService.saveTransaction(expense4);
        transactionService.saveTransaction(expense5);
        transactionService.saveTransaction(expense6);
        transactionService.saveTransaction(expense7);
        transactionService.saveTransaction(income);
    }

}
