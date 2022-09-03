package com.example.kafkademo.controller;

import com.example.kafkademo.entity.User;
import com.example.kafkademo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "user")
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "user/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable int id) {
        return ResponseEntity.ok().body(userService.getUser(id));
    }

    @GetMapping(path = "users")
    public ResponseEntity<List<User>> findAllUsers() {
        return ResponseEntity.ok().body(userService.findAll());
    }

}
