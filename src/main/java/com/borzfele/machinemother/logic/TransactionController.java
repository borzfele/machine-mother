package com.borzfele.machinemother.logic;

import com.borzfele.machinemother.services.TransactionService;
import com.borzfele.machinemother.model.Transaction;
import com.borzfele.machinemother.services.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TransactionController {

    private TransactionService transactionService;
    private UserServiceImpl userService;

    public TransactionController(TransactionService transactionService, UserServiceImpl userService) {
        this.transactionService = transactionService;
        this.userService = userService;
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
        transaction.setOwner(userService.getCurrentUser());
        transactionService.addTransaction(transaction);
        return "redirect:/index";
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
        transaction.setOwner(userService.getCurrentUser());
        transaction.setValue(transaction.getValue() * -1);
        transactionService.addTransaction(transaction);
        return "redirect:/index";
    }
}
