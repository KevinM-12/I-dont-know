package com.example.senati.controller;

import com.example.senati.model.User;
import com.example.senati.model.Response;
import com.example.senati.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/")
    public ResponseEntity<User> newUser(@RequestBody User user){
        return userService.newUser(user);
    }

    @DeleteMapping("/{id}")
    public Response deleteUser(@PathVariable int id){
        return  userService.deleteUser(id);
    }
    //public ResponseEntity<User> deleteUser(@PathVariable int id) {
    // userService.deleteUser(id);
    // return ResponseEntity.noContent().build();
    //}

    @PutMapping ("/{id}")
    public ResponseEntity<User> newUser(@PathVariable int id,@RequestBody User user){
        return userService.updateUser(id, user);
    }
}
