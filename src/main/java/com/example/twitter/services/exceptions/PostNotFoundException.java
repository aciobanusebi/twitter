package com.example.twitter.services.exceptions;

public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException(Integer id) {
        super(String.format("Post '%d' not found!", id));
    }
}