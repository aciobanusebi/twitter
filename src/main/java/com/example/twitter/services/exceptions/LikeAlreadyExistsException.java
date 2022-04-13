package com.example.twitter.services.exceptions;

public class LikeAlreadyExistsException extends RuntimeException {
    public LikeAlreadyExistsException(String userId, Integer postId) {
        super(String.format("User '%s' already likes post '%d'!", userId, postId));
    }
}
