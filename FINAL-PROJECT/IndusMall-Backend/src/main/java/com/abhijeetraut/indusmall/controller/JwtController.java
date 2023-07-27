package com.abhijeetraut.indusmall.controller;


import com.abhijeetraut.indusmall.entity.JwtRequest;
import com.abhijeetraut.indusmall.entity.JwtResponse;
import com.abhijeetraut.indusmall.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created By Abhijeet Raut on || Date : 21-04-2023 ||  Time : 10:05 AM.
 */

@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    private JwtService jwtService;


    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception{
        return jwtService.createJwtToken(jwtRequest);
    }

}
