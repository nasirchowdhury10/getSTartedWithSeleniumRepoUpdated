package com.example.demo.controller;

import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.UserDTO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

@GetMapping("/users")
public ResponseEntity <List<UserDTO>> fetchUser() {

//  UserService userService = new UserService();
    System.out.println(userService);

    return ResponseEntity.ok(userService.getUsers());

}

@GetMapping("/users/{id}")
public ResponseEntity<UserDTO> fetchUser(@PathVariable long id)  {

    return ResponseEntity.ok(userService.getUserById(id));

    }

    @GetMapping("/users/rand")
    public ResponseEntity<?> aRandomHandler(){throw new RuntimeException("Exception throw from controller ");}

    }