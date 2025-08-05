package com.example.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.exceptions.UserNotFound;
import com.example.todo.models.User;
import com.example.todo.services.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        userService.registerUser(user);
        return ResponseEntity.status(200).body(user);
    }
    @PostMapping("/login")
    public User login( @RequestBody User user) throws UserNotFound {
        User loginUser = userService.loginUser(user.getEmail(), user.getPassword());
        return loginUser;
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) throws UserNotFound {
        User user = userService.getUserById(id);
        return ResponseEntity.status(200).body(user);
    }
    
}
