package com.borzfele.machinemother.logic;

import com.borzfele.machinemother.dao.TransactionService;
import com.borzfele.machinemother.model.Transaction;
import com.borzfele.machinemother.repository.TransactionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/add-new-income", method = RequestMethod.GET)
    public String renderAddIncomeForm(Model model) {
        model.addAttribute("transaction", new Transaction());
        return "transaction-form";
    }

    @RequestMapping(value = "/add-new-income", method = RequestMethod.POST)
    public String showNewAddressForm(@ModelAttribute Transaction transaction) {
        transactionService.saveTransaction(transaction);
        return "redirect:/";
    }
}
