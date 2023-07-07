package com.skapps.cfweb.controllers;

import com.skapps.cfweb.dtos.AuthRequestDTO;
import com.skapps.cfweb.dtos.UserDTO;
import com.skapps.cfweb.dtos.UserRegistrationDTO;
import com.skapps.cfweb.services.UserService;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.AccountLockedException;

@RestController
@RequestMapping(value = "/user")
public class UserController implements BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = JSON)
    public void registerUser(@RequestBody UserRegistrationDTO request) {
        userService.registerUser(request);
    }

    @RequestMapping(value = "authenticate", method = RequestMethod.GET, consumes = JSON, produces = JSON)
    public UserDTO authenticate(@RequestBody AuthRequestDTO request) throws AccountLockedException { return userService.authenticateUser(request); }
}
