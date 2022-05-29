package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class paymentController {

    @GetMapping("/pay")
    public ResponseEntity<?> pay(){
        throw new RuntimeException("Exception throw from the ");
    }
}
