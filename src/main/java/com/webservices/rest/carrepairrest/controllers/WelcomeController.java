package com.webservices.rest.carrepairrest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/")
    public String showWelcomeMessage(){
        return "This awesome (car-repair) REST web-service welcomes you! :)";
    }
}
