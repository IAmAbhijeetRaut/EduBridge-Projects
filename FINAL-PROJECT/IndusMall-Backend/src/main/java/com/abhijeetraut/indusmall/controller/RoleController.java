package com.abhijeetraut.indusmall.controller;

import com.abhijeetraut.indusmall.entity.Role;
import com.abhijeetraut.indusmall.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By Abhijeet Raut on || Date : 12-04-2023 ||  Time : 12:43 PM.
 */
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;


    //For creating New Roles
    @PostMapping({"/createNewRole"})
    public Role createNewRole(@RequestBody Role role){
        return roleService.createNewRole(role);
    }
}
