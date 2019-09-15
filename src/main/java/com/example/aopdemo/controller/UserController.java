package com.example.aopdemo.controller;

import com.example.aopdemo.CustomLogAnotation;
import com.example.aopdemo.dao.UserDAO;
import com.example.aopdemo.model.User;
import com.example.aopdemo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserDAO userDAO;

    @GetMapping(path = "/", produces = "application/json")
    @CustomLogAnotation
    public Users getUsers() {
        return userDAO.getAllUsers();
    }

    @PostMapping(path= "/", consumes = "application/json", produces = "application/json")
    @CustomLogAnotation
    public ResponseEntity<Object> addUser(@RequestBody User user) {
        // Generate resource id
        Integer id = userDAO.getAllUsers().getUserList().size() + 1;
        user.setId(id);

        //add resource
        userDAO.addUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
