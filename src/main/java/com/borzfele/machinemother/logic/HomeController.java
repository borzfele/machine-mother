package com.borzfele.machinemother.logic;

import com.borzfele.machinemother.model.User;
import com.borzfele.machinemother.services.RoleService;
import com.borzfele.machinemother.services.TransactionService;
import com.borzfele.machinemother.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Calendar;

@Controller
public class HomeController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String handleRoot() {
        return "redirect:/index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String renderIndex(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User owner = userService.findByName(auth.getName());
        Calendar calendar = Calendar.getInstance();
        Calendar prevMonth = Calendar.getInstance();
        prevMonth.set(Calendar.MONTH, prevMonth.get(Calendar.MONTH) - 1);

        long incomeByDay = transactionService.getSumOfIncome(transactionService.findByYearAndMonthAndDayAndOwner(calendar, owner));
        long expensesByDay = transactionService.getSumOfExpenses(transactionService.findByYearAndMonthAndDayAndOwner(calendar, owner));
        long avgExpensesPerDay = transactionService.getAvgOfDailyExpensesByOwner(prevMonth, owner);
        long monthlyIncome = transactionService.getSumOfIncome(transactionService.findByYearAndMonthAndOwner(calendar, owner));
        long monthlyExpenses = transactionService.getSumOfExpenses(transactionService.findByYearAndMonthAndOwner(calendar, owner));

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
