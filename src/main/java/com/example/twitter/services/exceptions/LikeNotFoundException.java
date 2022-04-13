package com.example.twitter.services.exceptions;

public class LikeNotFoundException extends RuntimeException {

    public LikeNotFoundException(Integer id) {
        super(String.format("Like '%d' not found!", id));
    }
}