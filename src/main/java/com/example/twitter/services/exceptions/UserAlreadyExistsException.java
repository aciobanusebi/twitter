package com.example.twitter.services.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String id) {
        super(String.format("User '%s' already exists!", id));
    }
}
