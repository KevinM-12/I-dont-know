package com.example.senati.service;

import com.example.senati.model.Response;
import com.example.senati.model.User;
import com.example.senati.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
    //public void deleteUser(int id){
    // userRepository.deleteById(id);
    //}
    public  Response deleteUser(int id){
        Optional<User> optionalUser = userRepository.findById(id);
        Response response = new Response();
        if (optionalUser.isPresent()){
            response.setCode(200);
            response.setStastus("Succes");
            response.setMeseng("El usuario se elimino correctamente: "+id);
            return response;
        }
        response.setCode(400);
        response.setStastus("error");
        response.setMeseng("El usuario no se eilimino de manera correcta: "+id);
        return response;
    }


    // Metodo que actualizara las tablas
    public ResponseEntity<User> updateUser(int id, User user ){
        // Creamos una instancia de una lista de opcional donde esta integrado la tabla user donde usando la variable tipo clase userRepositri
        // Donde va encontrar por medio del id
        Optional<User> userOptional = userRepository.findById(id);
        // Se creara una nueva instancia de usuario
        User updateUser = new User();
        // Donde los set seleccionaran todos los campos de la tabla que se esta usando y se obtendran los datos con get gracias al metodo get and geter
        if (userOptional.isPresent()){
            updateUser.setId(id);
            updateUser.setName(user.getName());
            updateUser.setLastname(user.getLastname());
            return new ResponseEntity<>(userRepository.save(updateUser), HttpStatus.OK);
        }

        return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
    }
}
