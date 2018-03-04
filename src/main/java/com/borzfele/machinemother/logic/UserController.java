package com.borzfele.machinemother.logic;

import com.borzfele.machinemother.model.Role;
import com.borzfele.machinemother.model.User;
import com.borzfele.machinemother.services.RoleService;
import com.borzfele.machinemother.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    RoleService roleService;
    @Autowired
    UserServiceImpl userService;

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
        Role userRole = roleService.findByName("user");

        if (userRole != null) {
            user.getRoles().add(userRole);
        } else {
            Role freshlyCreatedUserRole = new Role("user");
            user.getRoles().add(freshlyCreatedUserRole);
        }

        user.setPassword(encriptor.encode(user.getPassword()));

        userService.saveUser(user);

        return "redirect:/login";
    }

}
