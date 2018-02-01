package com.borzfele.machinemother.logic;

import com.borzfele.machinemother.dao.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String renderIndex(Model model) {
        return "home/index";
    }
}
