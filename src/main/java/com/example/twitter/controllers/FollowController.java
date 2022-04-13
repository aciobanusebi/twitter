package com.example.twitter.controllers;

import com.example.twitter.services.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FollowController {
    @Autowired
    private FollowService followService;

    @PostMapping(value = "/follow/{idFollower}/{idFollowee}")
    public void follow(@PathVariable String idFollower, @PathVariable String idFollowee) {
        followService.follow(idFollower,idFollowee);
    }

    @DeleteMapping(value = "/follow/{idFollower}/{idFollowee}")
    public void unfollow(@PathVariable String idFollower, @PathVariable String idFollowee) {
        followService.unfollow(idFollower,idFollowee);
    }
}
