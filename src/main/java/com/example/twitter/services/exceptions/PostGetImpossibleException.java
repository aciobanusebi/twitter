package com.example.twitter.services.exceptions;

public class PostGetImpossibleException extends RuntimeException {

    public PostGetImpossibleException() {
        super("Cannot get posts since 'user' is not set!");
    }

    public PostGetImpossibleException(String key) {
        super(String.format("Cannot get posts since you cannot use '%s'!", key));
    }
}