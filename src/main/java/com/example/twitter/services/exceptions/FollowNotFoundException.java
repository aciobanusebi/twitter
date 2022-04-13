package com.example.twitter.services.exceptions;

public class FollowNotFoundException extends RuntimeException {

    public FollowNotFoundException(String idFollower, String idFollowee) {
        super(String.format("User '%s' does not already follow user '%s'!", idFollower, idFollowee));
    }
}