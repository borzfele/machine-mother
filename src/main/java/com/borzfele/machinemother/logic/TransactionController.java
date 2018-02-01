package com.borzfele.machinemother.logic;

import com.borzfele.machinemother.dao.TransactionService;
import com.borzfele.machinemother.model.Transaction;
import com.borzfele.machinemother.repository.TransactionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class TransactionController {

    private TransactionService transactionService;
    private TransactionRepository transactionRepository;

    public TransactionController(TransactionService transactionService, TransactionRepository transactionRepository) {
        this.transactionService = transactionService;
        this.transactionRepository = transactionRepository;
    }

    public void addTransaction(Transaction transaction) {
        transactionService.saveTransaction(transaction);
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
        List<Transaction> allTransactions = transactionService.getAll();

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
        List<Transaction> allTransactions = transactionService.getAll();

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
        List<Transaction> everyTransaction = transactionService.getAll();
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

        return expensesInGivenMonth;
    }

    public long getAvgOfDailyExpensesOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        long sum = 0;
        List<Transaction> dailyExpenses = new ArrayList<Transaction>();

        dailyExpenses = getExpensesOfLastMonth(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));

        for (Transaction transaction : dailyExpenses) {
            sum += transaction.getValue();
        }

        if (dailyExpenses.size() != 0) {
            return sum / dailyExpenses.size();
        } else {
            return 0;
        }
    }


    @RequestMapping(value = "/add-new-income", method = RequestMethod.GET)
    public String renderAddIncomeForm(Model model) {
        model.addAttribute("transaction", new Transaction());
        return "income-form";
    }

    @RequestMapping(value = "/add-new-income", method = RequestMethod.POST)
    public String saveIncome(@ModelAttribute Transaction transaction, @RequestParam(name = "isCont", required = false) String[] isCont) {
        if (isCont != null) {
            transaction.setContinous(true);
        } else {
            transaction.setContinous(false);
        }
        transactionService.saveTransaction(transaction);
        return "redirect:/";
    }

    @RequestMapping(value = "/add-new-expense", method = RequestMethod.GET)
    public String renderAddExpenseForm(Model model) {
        model.addAttribute("transaction", new Transaction());
        return "expense-form";
    }

    @RequestMapping(value = "/add-new-expense", method = RequestMethod.POST)
    public String saveExpense(@ModelAttribute Transaction transaction, @RequestParam(name = "isCont", required = false) String[] isCont) {
        if (isCont != null) {
            transaction.setContinous(true);
        } else {
            transaction.setContinous(false);
        }
        transaction.setValue(transaction.getValue() * -1);
        transactionService.saveTransaction(transaction);
        return "redirect:/";
    }
}
