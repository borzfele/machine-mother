package com.borzfele.machinemother.dao;

import com.borzfele.machinemother.model.Transaction;
import com.borzfele.machinemother.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.tree.TreeNode;
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
        saveTransaction(transaction);
    }

    public long getAvgOf(List<Transaction> listOfTransactions) {
        long sumOfIncome = 0;

        if (listOfTransactions != null) {
            for (Transaction transaction : listOfTransactions) {
                sumOfIncome += transaction.getValue();
            }
        }

        return sumOfIncome / listOfTransactions.size();
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

    private List<Transaction> getExpensesOfLastMonth(int yearId, int monthId) {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        List<Transaction> everyTransaction = getAll();
        System.out.println(everyTransaction);
        List<Transaction> expensesInGivenMonth = new ArrayList<Transaction>();

        if (calendar.get(Calendar.MONTH) == Calendar.JANUARY) {
            for (Transaction transaction : everyTransaction) {
                if (transaction.getDate().get(Calendar.YEAR) == currentYear - 1
                        && transaction.getDate().get(Calendar.MONTH) == Calendar.DECEMBER) {
                    expensesInGivenMonth.add(transaction);
                }
            }
        } else {
            for (Transaction transaction : everyTransaction) {
                if (transaction.getDate().get(Calendar.YEAR) == currentYear
                        && transaction.getDate().get(Calendar.MONTH) == currentMonth - 1) {
                    expensesInGivenMonth.add(transaction);
                }
            }
        }
        System.out.println(expensesInGivenMonth);
        return expensesInGivenMonth;
    }

    public long getAvgOfDailyExpensesOfLastMonth() {
        Calendar calendar = new GregorianCalendar();
        long sum = 0;
        List<Transaction> dailyExpenses = getExpensesOfLastMonth();

        for (Transaction transaction : dailyExpenses) {
            sum += transaction.getValue();
        }

        if (dailyExpenses.size() != 0) {
            return sum / dailyExpenses.size();
        } else {
            return 0;
        }
    }


}
