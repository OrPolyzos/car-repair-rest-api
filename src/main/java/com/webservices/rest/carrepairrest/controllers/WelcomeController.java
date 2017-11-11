package com.webservices.rest.carrepairrest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/")
    public String showWelcomeMessage(){
        return "Welcome to this awesome REST web-service for a car-repair shop!";
    }
}
