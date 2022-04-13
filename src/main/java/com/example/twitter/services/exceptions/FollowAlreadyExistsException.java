package com.example.twitter.services.exceptions;

public class FollowAlreadyExistsException extends RuntimeException {

    public FollowAlreadyExistsException(String follower, String followee) {
        super(String.format("User '%s' already follows '%s'!", follower, followee));
    }
}
