package com.example.twitter.controllers;

import com.example.twitter.data.entities.Like;
import com.example.twitter.data.entities.Post;
import com.example.twitter.services.LikeService;
import com.example.twitter.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.List;
import java.util.Map;


@RestController
public class LikeController {
    @Autowired
    private LikeService likeService;

    @PostMapping(value = "/like/{userId}/{postId}")
    public void likePost(@PathVariable String userId,
                         @PathVariable Integer postId) {
        likeService.likePost(userId,postId);
    }

    @DeleteMapping(value = "/like/{likeId}")
    public void removeLike(@PathVariable Integer likeId) {
        likeService.removeLike(likeId);
    }

    @GetMapping(value = "/like/{likeId}")
    public Like getLike(@PathVariable Integer likeId) {
        return likeService.getLike(likeId);
    }

}
