package com.example.twitter.services;


import com.example.twitter.data.entities.Follow;
import com.example.twitter.data.entities.User;
import com.example.twitter.data.entities.compositepk.FollowId;
import com.example.twitter.data.repositories.FollowRepository;
import com.example.twitter.data.repositories.UserRepository;
import com.example.twitter.services.exceptions.FollowAlreadyExistsException;
import com.example.twitter.services.exceptions.FollowNotFoundException;
import com.example.twitter.services.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Optional;

@Service
public class FollowService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowRepository followRepository;


    public void follow(String idFollower, String idFollowee) {
        Follow follow = new Follow();
        Optional<User> follower = userRepository.findById(idFollower);
        Optional<User> followee = userRepository.findById(idFollowee);
        if(follower.isEmpty()) {
            throw new UserNotFoundException(idFollower);
        }
        if(followee.isEmpty()) {
            throw new UserNotFoundException(idFollowee);
        }
        FollowId followId = new FollowId();
        followId.setUserId(idFollower);
        followId.setFollowingUserId(idFollowee);

        Optional<Follow> retrievedFollow = followRepository.findById(followId);
        if(retrievedFollow.isPresent()) {
            throw new FollowAlreadyExistsException(idFollower,idFollowee);
        }

        follow.setId(followId);
//        follow.setUsersByUserId(follower.get());
//        follow.setUsersByFollowingUserId(followee.get());
        follow.setTimestamp(new Timestamp(System.currentTimeMillis()).getTime());

        followRepository.save(follow);
    }

    @Transactional
    public void unfollow(String idFollower, String idFollowee) {
        Optional<User> follower = userRepository.findById(idFollower);
        Optional<User> followee = userRepository.findById(idFollowee);
        if(follower.isEmpty()) {
            throw new UserNotFoundException(idFollower);
        }
        if(followee.isEmpty()) {
            throw new UserNotFoundException(idFollowee);
        }
        FollowId followId = new FollowId();
        followId.setUserId(idFollower);
        followId.setFollowingUserId(idFollowee);

        Optional<Follow> retrievedFollow = followRepository.findById(followId);
        if(retrievedFollow.isEmpty()) {
            throw new FollowNotFoundException(idFollower,idFollowee);
        }

        followRepository.deleteById(followId);
    }
}
