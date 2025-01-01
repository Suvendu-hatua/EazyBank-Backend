package com.easy_bytes_spring_security.EazyBytes_Spring_Security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardsController {

    @GetMapping("/myCards")
    public  String getCardsDetails () {
        return "Here are the card details from the DB";
    }

}