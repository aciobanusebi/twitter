package com.example.twitter.services;


import com.example.twitter.controllers.ReplyController;
import com.example.twitter.data.entities.*;
import com.example.twitter.data.repositories.*;
import com.example.twitter.services.exceptions.LikeAlreadyExistsException;
import com.example.twitter.services.exceptions.LikeNotFoundException;
import com.example.twitter.services.exceptions.PostNotFoundException;
import com.example.twitter.services.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class ReplyService {
    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MentionRepository mentionRepository;

    @Autowired
    private PostService postService;

    public void replyPost(ReplyController.ReplyRequestBody replyRequestBody) {
        Optional<User> user = userRepository.findById(replyRequestBody.user);
        if(user.isEmpty()) {
            throw new UserNotFoundException(replyRequestBody.user);
        }
        Optional<Post> parentPost = postRepository.findById(replyRequestBody.parentPostId);
        if(parentPost.isEmpty()) {
            throw new PostNotFoundException(replyRequestBody.parentPostId);
        }
//      ALTERNATIVE:
//        User userr = new User();
//        userr.setId(replyRequestBody.user);


        Reply reply = new Reply();
        reply.setUsersByUserId(user.get());
        reply.setMessage(replyRequestBody.message);
        reply.setTimestamp(new Timestamp(System.currentTimeMillis()).getTime());
        reply.setIsPublic(replyRequestBody.isPublic);
//      ALTERNATIVE:
//        Post parentPostt = new Post();
//        parentPostt.setId(replyRequestBody.parentPostId);
        reply.setPostsByParentPostId(parentPost.get());
        replyRepository.save(reply);



        for (String localUserId : replyRequestBody.mentions) {
            Mention mention = new Mention();
            User localUser = new User();
            localUser.setId(localUserId);

            mention.setPostsByPostId(reply);
            mention.setUsersByUserId(localUser);

            mentionRepository.save(mention);
        }

        // NOT WORKING:
//        Post post = postService.addPostProcessings(replyRequestBody.user,
//                replyRequestBody.message,
//                replyRequestBody.mentions);
//
//        Reply reply = new Reply();
//
////        Post finalParentPost = new Post();
////        finalParentPost.setId(parentPost.get().getId());
////        reply.setPostsByParentPostId(finalParentPost);
////
////        Post finalPost = new Post();
////        finalPost.setId(post.getId());
////        reply.setPostsByPostId(finalPost);
//
//        System.out.println(parentPost.get().getId());
//        System.out.println(post.getId());
//
//        reply.setPostsByParentPostId(parentPost.get());
//        reply.setPostsByPostId(post);
//
//        reply.setUsersByUserId(user.get());
//        reply.setMessage(replyRequestBody.message);
//        reply.setTimestamp(new Timestamp(System.currentTimeMillis()).getTime());
//
//        reply.setIsPublic(replyRequestBody.isPublic);
//        replyRepository.save(reply);
    }
}
