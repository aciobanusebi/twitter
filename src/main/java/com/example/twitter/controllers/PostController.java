package com.example.twitter.controllers;

import com.example.twitter.data.entities.Follow;
import com.example.twitter.data.entities.Post;
import com.example.twitter.data.entities.User;
import com.example.twitter.services.PostService;
import com.example.twitter.services.UserService;
import com.example.twitter.services.exceptions.UserSearchImpossibleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;



@RestController
public class PostController {
    @Autowired
    private PostService postService;

    public static class AddPostRequestBody {
        @NotBlank(message = "Name is mandatory")
        public String user;
        @NotBlank(message = "Message is mandatory")
        public String message;
        public List<String> mentions;
    }

    @PostMapping(value = "/post", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addPost(@Valid @RequestBody AddPostRequestBody json) {
        postService.addPost(json);
    }

    @GetMapping(value = "/post")
    public Collection<Post> getPosts(@RequestParam Map<String,String> requestParams) {
        return postService.getPosts(requestParams);
    }

    @GetMapping(value = "/feed/{userId}")
    public Collection<Post> getFeed(@PathVariable String userId) {
        return postService.getFeed(userId);
    }

    @DeleteMapping(value = "/post/{id}")
    public void deletePost(@PathVariable Integer id) {
        postService.deletePost(id);
    }

    @PostMapping(value = "/repost/{id}")
    public void repostPost(@PathVariable Integer id,
                           @RequestParam String newUser) {
        postService.repostPost(id,newUser);
    }

    @GetMapping(value = "/mentions/{userId}")
    public Collection<Post> getMentions(@PathVariable String userId) {
        return postService.getMentions(userId);
    }
}
