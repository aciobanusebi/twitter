package com.example.twitter.controllers;

import com.example.twitter.data.entities.User;
import com.example.twitter.services.FollowService;
import com.example.twitter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/users/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerUser(@PathVariable String id, @RequestBody User user) {
        user.setId(id);
        userService.registerUser(user);
    }

    @DeleteMapping(value = "/users/{id}")
    public void unregisterUser(@PathVariable String id) {
        userService.deleteUser(id);
    }

    @GetMapping(value = "/users/{id}")
    public Optional<User> getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @GetMapping(value = "/users")
    public List<User> searchUser(@RequestParam Map<String,String> requestParams) {
        return userService.searchUser(requestParams);
    }

}
