package com.borzfele.machinemother.controllers;


import com.borzfele.machinemother.models.User;
import com.borzfele.machinemother.models.UserCheckResponseBody;
import com.borzfele.machinemother.services.UserServiceImpl;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
public class RegistrationController {

    private final static Logger logger = LoggerFactory.getLogger(RegistrationController.class);
    private UserServiceImpl userService;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/check-username")
    public ResponseEntity<?> getSearchResultViaAjax(@Valid @RequestBody String username, Errors errors) {

        Gson gson = new Gson();
        UserCheckResponseBody result = new UserCheckResponseBody();

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);

        }

        if (userService.findByName(gson.fromJson(username, String.class)) != null) {
            result.setMsg("username is in use already");
            result.setResult(true);
        } else {
            result.setMsg("you can use this name.");
            result.setResult(false);
        }

        return ResponseEntity.ok(result);

    }
}

