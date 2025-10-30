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
@CrossOrigin("http://localhost:4200")
public class UserController {
    @Autowired
    UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/api/users")
    public ResponseEntity<User> newUser(@RequestBody User user){
        return userService.newUser(user);
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<Response> deleteUser(@PathVariable int id){
        return  userService.deleteUser(id);
    }
    //public ResponseEntity<User> deleteUser(@PathVariable int id) {
    // userService.deleteUser(id);
    // return ResponseEntity.noContent().build();
    //}

    @PutMapping ("/api/users/{id}")
    public ResponseEntity<User> newUser(@PathVariable int id,@RequestBody User user){
        return userService.updateUser(id, user);
    }
}
