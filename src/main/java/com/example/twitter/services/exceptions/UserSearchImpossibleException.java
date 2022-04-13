package com.example.twitter.services.exceptions;

public class UserSearchImpossibleException extends RuntimeException {

    public UserSearchImpossibleException() {
        super("Cannot search users!");
    }

    public UserSearchImpossibleException(String method) {
        super(String.format("Cannot search users by '%s'!", method));
    }
}