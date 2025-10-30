package com.example.senati.service;

import com.example.senati.model.Response;
import com.example.senati.model.User;
import com.example.senati.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public ResponseEntity<User> newUser(User user){
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }

    public ResponseEntity<Response> deleteUser(@PathVariable int id){
        Optional<User> optionalUser = userRepository.findById(id);
        Response response = new Response();

        if (optionalUser.isPresent()){
            response.setCode(200);
            response.setStastus("Succes");
            response.setMeseng("El usuario se elimino correctamente: "+id);
            return ResponseEntity.ok(response);
        }
        response.setCode(400);
        response.setStastus("error");
        response.setMeseng("El usuario no se eilimino de manera correcta: "+id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }


    public ResponseEntity<User> updateUser(int id, User user ){
        Optional<User> userOptional = userRepository.findById(id);
        User updateUser = new User();
        if (userOptional.isPresent()){
            updateUser.setId(id);
            updateUser.setName(user.getName());
            updateUser.setLastname(user.getLastname());
            return new ResponseEntity<>(userRepository.save(updateUser), HttpStatus.OK);
        }

        return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
    }
}
