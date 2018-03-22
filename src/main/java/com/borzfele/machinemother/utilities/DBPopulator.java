package com.borzfele.machinemother.utilities;

import com.borzfele.machinemother.models.Role;
import com.borzfele.machinemother.models.User;
import com.borzfele.machinemother.services.RoleService;
import com.borzfele.machinemother.services.TransactionService;
import com.borzfele.machinemother.models.Transaction;
import com.borzfele.machinemother.services.UserServiceImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Component
public class DBPopulator {

    public DBPopulator(TransactionService transactionService, RoleService roleService, UserServiceImpl userService) {

        User user = new User();
        user.setName("asd");
        user.setPassword("asd");

        PasswordEncoder encriptor = new BCryptPasswordEncoder();
        Role userRole = roleService.findByName("user");

        if (userRole != null) {
            user.getRoles().add(userRole);
        } else {
            Role freshlyCreatedUserRole = new Role("user");
            user.getRoles().add(freshlyCreatedUserRole);
        }

        user.setPassword(encriptor.encode(user.getPassword()));
        userService.saveUser(user);

        Calendar date1 = new GregorianCalendar(2018,0, 19, 20, 12, 1);
        Calendar date2 = new GregorianCalendar(2018,0, 21, 20, 12, 1);
        Calendar date3 = new GregorianCalendar(2018,0, 22, 20, 12, 1);
        Calendar date4 = new GregorianCalendar(2018,0, 23, 20, 12, 1);
        Calendar date5 = new GregorianCalendar(2018,0, 24, 20, 12, 1);
        Calendar date6 = new GregorianCalendar(2018,1, 24, 20, 12, 1);

        Transaction expense1 = new Transaction("pizza", -1490, "Marximban, jóság volt.", user);
        Transaction expense2 = new Transaction("banánsöröcs", -590, "Marximban, jóság volt.", user);
        Transaction expense3 = new Transaction("rózsa", -600, "random csőlakó nénitől", user);
        Transaction expense4 = new Transaction("bevásárlás", -12345, "listán látod", user);
        Transaction expense5 = new Transaction("bevásárlás", -3245, "listán látod", user);
        Transaction expense6 = new Transaction("bevásárlás", -4321, "listán látod", user);
        Transaction expense7 = new Transaction("bevásárlás", -567, "listán látod", user);
        Transaction expense8 = new Transaction("bevásárlás", -567, "listán látod", user);
        Transaction expense9 = new Transaction("bevásárlás", -567, "listán látod", user);

        Transaction income = new Transaction("pizza", 43000, "fizuka", true, user);
        Transaction income2 = new Transaction("pizza", 43000, "fizuka", true, user);

        expense1.setYear(date1.get(Calendar.YEAR));
        expense1.setMonth(date1.get(Calendar.MONTH));
        expense1.setDay(date1.get(Calendar.DAY_OF_MONTH));
        expense2.setYear(date1.get(Calendar.YEAR));
        expense2.setMonth(date1.get(Calendar.MONTH));
        expense2.setDay(date1.get(Calendar.DAY_OF_MONTH));
        expense3.setYear(date1.get(Calendar.YEAR));
        expense3.setMonth(date1.get(Calendar.MONTH));
        expense3.setDay(date1.get(Calendar.DAY_OF_MONTH));
        expense4.setYear(date2.get(Calendar.YEAR));
        expense4.setMonth(date2.get(Calendar.MONTH));
        expense4.setDay(date2.get(Calendar.DAY_OF_MONTH));
        expense5.setYear(date3.get(Calendar.YEAR));
        expense5.setMonth(date3.get(Calendar.MONTH));
        expense5.setDay(date3.get(Calendar.DAY_OF_MONTH));
        expense6.setYear(date4.get(Calendar.YEAR));
        expense6.setMonth(date4.get(Calendar.MONTH));
        expense6.setDay(date4.get(Calendar.DAY_OF_MONTH));
        expense7.setYear(date5.get(Calendar.YEAR));
        expense7.setMonth(date5.get(Calendar.MONTH));
        expense7.setDay(date5.get(Calendar.DAY_OF_MONTH));
        expense8.setYear(date6.get(Calendar.YEAR));
        expense8.setMonth(date6.get(Calendar.MONTH));
        expense8.setDay(date6.get(Calendar.DAY_OF_MONTH));
        income.setYear(date1.get(Calendar.YEAR));
        income.setMonth(date1.get(Calendar.MONTH));
        income.setDay(date1.get(Calendar.DAY_OF_MONTH));
        income2.setYear(date6.get(Calendar.YEAR));
        income2.setMonth(date6.get(Calendar.MONTH));
        income2.setDay(date6.get(Calendar.DAY_OF_MONTH));

        transactionService.saveTransaction(expense1);
        transactionService.saveTransaction(expense2);
        transactionService.saveTransaction(expense3);
        transactionService.saveTransaction(expense4);
        transactionService.saveTransaction(expense5);
        transactionService.saveTransaction(expense6);
        transactionService.saveTransaction(expense7);
        transactionService.saveTransaction(income);
        transactionService.saveTransaction(expense8);
        transactionService.saveTransaction(income2);

    }

}
