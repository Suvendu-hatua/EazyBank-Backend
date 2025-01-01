package com.easy_bytes_spring_security.EazyBytes_Spring_Security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoansController {

    @GetMapping("/myLoans")
    public  String getLoansDetails () {
        return "Here are the loans details from the DB";
    }

}
