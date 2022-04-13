package com.example.twitter.services;


import com.example.twitter.controllers.PostController;
import com.example.twitter.data.entities.*;
import com.example.twitter.data.repositories.LikeRepository;
import com.example.twitter.data.repositories.MentionRepository;
import com.example.twitter.data.repositories.PostRepository;
import com.example.twitter.data.repositories.UserRepository;
import com.example.twitter.services.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    public void likePost(String userId, Integer postId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            throw new UserNotFoundException(userId);
        }
        Optional<Post> post = postRepository.findById(postId);
        if(post.isEmpty()) {
            throw new PostNotFoundException(postId);
        }
        if(likeRepository.findByUsersByUserId_IdAndPostsByPostId_Id(userId,postId).isPresent()) {
            throw new LikeAlreadyExistsException(userId,postId);
        }

        Like like = new Like();
        like.setPostsByPostId(post.get());
        like.setUsersByUserId(user.get());
        likeRepository.save(like);
    }

    public void removeLike(Integer likeId) {
        Optional<Like> like = likeRepository.findById(likeId);
        if(like.isEmpty()) {
            throw new LikeNotFoundException(likeId);
        }
        likeRepository.deleteById(likeId);
    }

    public Like getLike(Integer likeId) {
        Optional<Like> like = likeRepository.findById(likeId);
        if(like.isEmpty()) {
            throw new LikeNotFoundException(likeId);
        }
        return like.get();
    }
}
