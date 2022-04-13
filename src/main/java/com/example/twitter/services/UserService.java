package com.example.twitter.services;

import com.example.twitter.data.entities.Follow;
import com.example.twitter.data.entities.User;
import com.example.twitter.data.repositories.FollowRepository;
import com.example.twitter.data.repositories.UserRepository;
import com.example.twitter.services.exceptions.UserAlreadyExistsException;
import com.example.twitter.services.exceptions.UserNotFoundException;
import com.example.twitter.services.exceptions.UserSearchImpossibleException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void registerUser(User user) {
        if(userRepository.findById(user.getId()).isEmpty()) {
            userRepository.save(user);
        } else {
            throw new UserAlreadyExistsException(user.getId());
        }

    }

    public Optional<User> getUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException(id);
        }
        return user;
    }

    public List<User> searchUser(Map<String,String> requestParams) {
        List<String> acceptedKeys = List.of("firstName","lastName");
        for (Map.Entry<String, String> entry : requestParams.entrySet()) {
            if(!acceptedKeys.contains(entry.getKey())) {
                throw new UserSearchImpossibleException(entry.getKey());
            }
        }

        String firstName=requestParams.get("firstName");
        String lastName=requestParams.get("lastName");
        if(firstName != null && lastName != null) {
            return userRepository.findByFirstNameAndLastName(firstName,lastName);
        }
        if(firstName != null) {
            return userRepository.findByFirstName(firstName);
        }
        if(lastName != null) {
            return userRepository.findByLastName(lastName);
        }

        throw new UserSearchImpossibleException();
    }


    public void deleteUser(String id) {
        if(userRepository.findById(id).isEmpty()) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }
}
