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

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
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
