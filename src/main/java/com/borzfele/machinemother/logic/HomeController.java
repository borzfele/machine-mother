package com.borzfele.machinemother.logic;

import com.borzfele.machinemother.dao.TransactionService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Calendar;
import java.util.Date;

@Controller
public class HomeController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String renderIndex(Model model) {

        model.addAttribute("sumOfDailyIncome", transactionService.getSumOfIncomeByDay(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));

        return "home/index";
    }
}
