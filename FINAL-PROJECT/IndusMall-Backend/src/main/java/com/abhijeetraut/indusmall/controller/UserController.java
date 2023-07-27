package com.abhijeetraut.indusmall.controller;

import com.abhijeetraut.indusmall.entity.User;
import com.abhijeetraut.indusmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By Abhijeet Raut on || Date : 20-04-2023 ||  Time : 04:13 PM.
 */

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user){
        return userService.registerNewUser(user);
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('ADMIN')")
    public String forAdmin(){
        return "THIS IS ADMIN PAGE";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('USER')")
    public String forUser(){
        return "THIS IS USER PAGE";
    }


}
