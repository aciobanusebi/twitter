package com.example.twitter.controllers;

import com.example.twitter.data.entities.Like;
import com.example.twitter.services.LikeService;
import com.example.twitter.services.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@RestController
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    public static class ReplyRequestBody extends PostController.AddPostRequestBody {
        @NotNull(message = "parentPostId is mandatory")
        public Integer parentPostId;

        @NotNull(message = "isPublic is mandatory")
        public Boolean isPublic;
    }

    @PostMapping(value = "/reply")
    public void replyPost(@Valid @RequestBody ReplyRequestBody json) {
        replyService.replyPost(json);
    }





//
//    @DeleteMapping(value = "/like/{likeId}")
//    public void removeLike(@PathVariable Integer likeId) {
//        likeService.removeLike(likeId);
//    }

}
