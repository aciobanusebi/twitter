package com.example.twitter.services.exceptions;

public class PostAddImpossibleException extends RuntimeException {

    public PostAddImpossibleException() {
        super("Cannot add post since at least one of the keys ['user','message'] is missing!");
    }

    public PostAddImpossibleException(String key) {
        super(String.format("Cannot use key '%s' when adding posts!", key));
    }
}