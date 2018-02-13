package com.borzfele.machinemother.logic;

import com.borzfele.machinemother.dao.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Calendar;

@Controller
public class HomeController {

    @Autowired
    private TransactionService transactionService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String renderIndex(Model model) {

        long incomeByDay = transactionService.getSumOfIncomeByDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        long expensesByDay = transactionService.getSumOfExpensesByDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        long avgExpensesPerDay = transactionService.getAvgOfDailyExpensesOfLastMonth();

        model.addAttribute("sumOfDailyIncome", incomeByDay);
        model.addAttribute("sumOfDailyExpenses", expensesByDay);
        model.addAttribute("dailyBalance", incomeByDay + expensesByDay);
        model.addAttribute("avgExpensesPerDay", avgExpensesPerDay);

        return "home/index";
    }
}
