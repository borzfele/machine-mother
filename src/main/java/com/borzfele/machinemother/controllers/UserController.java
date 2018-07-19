package com.borzfele.machinemother.controllers;

import com.borzfele.machinemother.models.ActivationCode;
import com.borzfele.machinemother.models.Role;
import com.borzfele.machinemother.models.User;
import com.borzfele.machinemother.services.ActivationCodeService;
import com.borzfele.machinemother.services.EmailService;
import com.borzfele.machinemother.services.RoleService;
import com.borzfele.machinemother.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    private final RoleService roleService;
    private final UserServiceImpl userService;
    private final EmailService emailService;
    private final ActivationCodeService activationCodeService;

    @Autowired
    public UserController(RoleService roleService, UserServiceImpl userService, EmailService emailService, ActivationCodeService activationCodeService) {
        this.roleService = roleService;
        this.userService = userService;
        this.emailService = emailService;
        this.activationCodeService = activationCodeService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String renderRegForm(Model model) {
        User user = new User();
        user.addRole(roleService.findByName("user"));
        roleService.findByName("user");
        model.addAttribute("user", user);

        return "authentication/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute User user) {

        PasswordEncoder encriptor = new BCryptPasswordEncoder();
        ActivationCode activationCode = new ActivationCode();
        Role userRole = roleService.findByName("user");

        String actCode = activationCode.getCode();

        while (activationCodeService.findByActivationCode(actCode) == null) {
            actCode = activationCode.getCode();
        }

        activationCode.setActivationCode(actCode);

        user.setActivationCode(activationCode);
        user.setEnabled(false);
        user.setPassword(encriptor.encode(user.getPassword()));


        String text =
                "Dear " + user.getName() + ", \n\nI can't thank you enough for your trust.\n" +
                "I know, it probably was a hard decision for you, " +
                "but I still promise my help in regain your social capabilities, " +
                "confidence and contentment in this world.\n\n" +
                "To finish registration, please click on this confirmation link." +
                "This will redirect you to the site.\n" +
                "Be as bad as you want to be,\n" +
                "Mom";

        //emailService.sendMessage(user.getEmail(), user.getName());
        userService.saveUser(user);

        return "redirect:/login";
    }
}
