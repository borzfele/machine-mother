package com.borzfele.machinemother.logic;

import com.borzfele.machinemother.services.TransactionService;
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
        Calendar calendar = Calendar.getInstance();
        Calendar prevMonth = Calendar.getInstance();
        prevMonth.set(Calendar.MONTH, prevMonth.get(Calendar.MONTH) - 1);

        long incomeByDay = transactionService.getSumOfIncome(transactionService.findByYearAndMonthAndDay(calendar));
        long expensesByDay = transactionService.getSumOfExpenses(transactionService.findByYearAndMonthAndDay(calendar));
        long avgExpensesPerDay = transactionService.getAvgOfDailyExpenses(prevMonth);
        long monthlyIncome = transactionService.getSumOfIncome(transactionService.findByYearAndMonth(calendar));
        long monthlyExpenses = transactionService.getSumOfExpenses(transactionService.findByYearAndMonth(calendar));

        model.addAttribute("sumOfDailyIncome", incomeByDay);
        model.addAttribute("sumOfDailyExpenses", expensesByDay);
        model.addAttribute("dailyBalance", incomeByDay - expensesByDay);
        model.addAttribute("avgExpensesPerDay", avgExpensesPerDay);
        model.addAttribute("monthlyIncome", monthlyIncome);
        model.addAttribute("monthlyExpenses", monthlyExpenses);
        model.addAttribute("monthlyBalance", monthlyIncome - monthlyExpenses);

        return "home/index";
    }
}
