package com.example.twitter.services;


import com.example.twitter.controllers.PostController;
import com.example.twitter.data.entities.Follow;
import com.example.twitter.data.entities.Mention;
import com.example.twitter.data.entities.Post;
import com.example.twitter.data.entities.User;
import com.example.twitter.data.repositories.MentionRepository;
import com.example.twitter.data.repositories.PostRepository;
import com.example.twitter.data.repositories.UserRepository;
import com.example.twitter.services.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MentionRepository mentionRepository;

    public Post addPostProcessings(String userId, String message, List<String> mentions) {
        User user = new User();
        user.setId(userId);

        Post post = new Post();
        post.setUsersByUserId(user);
        post.setMessage(message);
        post.setTimestamp(new Timestamp(System.currentTimeMillis()).getTime());
        post=postRepository.save(post);

        if(mentions != null) {
            for (String localUserId : mentions) {
                Mention mention = new Mention();
                User localUser = new User();
                localUser.setId(localUserId);

                mention.setPostsByPostId(post);
                mention.setUsersByUserId(localUser);

                mentionRepository.save(mention);
            }
        }

        return post;
    }

    public void addPost(PostController.AddPostRequestBody json) {
//        final String USER_KEY="user", MESSAGE_KEY="message", MENTIONS_KEY = "mentions";
//        List<String> acceptedKeys = List.of(USER_KEY,MESSAGE_KEY,MENTIONS_KEY);
//        for (Map.Entry<String, String> entry : json.entrySet()) {
//            if(!acceptedKeys.contains(entry.getKey())) {
//                throw new PostAddImpossibleException(entry.getKey());
//            }
//        }

//        if(!json.containsKey(USER_KEY) || !json.containsKey(MESSAGE_KEY)) {
//            throw new PostAddImpossibleException();
//        }

//        if(!json.containsKey(USER_KEY) || !json.containsKey(MESSAGE_KEY)) {
//            throw new PostAddImpossibleException();
//        }

//        if(json.containsKey(MENTIONS_KEY)) {
//            List<String> userMentions = Collections.singletonList(json.get(MENTIONS_KEY));
//            System.out.println(userMentions);
//        }

        if(!userRepository.existsById(json.user)) {
            throw new UserNotFoundException(json.user);
        }

        if(json.mentions != null) {
            for (String userId : json.mentions) {
                if(!userRepository.existsById(userId)) {
                    throw new UserNotFoundException(userId);
                }
            }
        }


        addPostProcessings(json.user, json.message, json.mentions);
    }

    public Collection<Post> getPosts(Map<String,String> requestParams) {
        final String USER_KEY = "user", TIMESTAMP_KEY="minTimestamp";
        List<String> acceptedKeys = List.of(USER_KEY,TIMESTAMP_KEY);
        for (Map.Entry<String, String> entry : requestParams.entrySet()) {
            if(!acceptedKeys.contains(entry.getKey())) {
                throw new PostGetImpossibleException(entry.getKey());
            }
        }

        if(!requestParams.containsKey(USER_KEY)) {
            throw new PostGetImpossibleException();
        }

        String userId = requestParams.get(USER_KEY);
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            throw new UserNotFoundException(userId);
        }

        if(requestParams.containsKey(TIMESTAMP_KEY)) {
            long minTimestamp = Long.parseLong(requestParams.get(TIMESTAMP_KEY));
            return postRepository.findByUsersByUserId_IdAndTimestampGreaterThan(
                    userId,
                    minTimestamp
            );
        }

        return user.get().getPostsById();
    }

    public Collection<Post> getFeed(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()) {
            throw new UserNotFoundException(userId);
        }
        User user = optionalUser.get();
        Collection<Follow> follows = user.getFollowsById();
        Collection<Post> result = new LinkedList<>();
        for (Follow follow : follows) {
            Collection<Post> posts=follow.getUsersByFollowingUserId().getPostsById();
            result.addAll(posts);
        }
        return result;
    }

    public void deletePost(Integer id) {
        if(postRepository.findById(id).isEmpty()) {
            throw new PostNotFoundException(id);
        }
        postRepository.deleteById(id);
    }

    public void repostPost(Integer id, String newUser) {
        Optional<Post> initialPost = postRepository.findById(id);
        if(initialPost.isEmpty()) {
            throw new PostNotFoundException(id);
        }
        String message = initialPost.get().getMessage();
        Optional<User> user = userRepository.findById(newUser);
        if(user.isEmpty()) {
            throw new UserNotFoundException(newUser);
        }
        Post post = new Post();
        post.setMessage(message);
        post.setUsersByUserId(user.get());
        post.setTimestamp(new Timestamp(System.currentTimeMillis()).getTime());
        postRepository.save(post);
    }

    public Collection<Post> getMentions(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            throw new UserNotFoundException(userId);
        }
        Collection<Mention> mentions = user.get().getMentionsById();
        Collection<Post> result = new LinkedList<>();
        for(Mention mention: mentions) {
            result.add(mention.getPostsByPostId());
        }
        return result;
    }
}
