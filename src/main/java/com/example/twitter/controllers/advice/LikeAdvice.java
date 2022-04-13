package com.example.twitter.controllers.advice;

import com.example.twitter.services.exceptions.FollowAlreadyExistsException;
import com.example.twitter.services.exceptions.FollowNotFoundException;
import com.example.twitter.services.exceptions.LikeAlreadyExistsException;
import com.example.twitter.services.exceptions.LikeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class LikeAdvice {
    @ResponseBody
    @ExceptionHandler(LikeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String likeNotFoundHandler(LikeNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(LikeAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String likeAlreadyExistsHandler(LikeAlreadyExistsException ex) {
        return ex.getMessage();
    }
}
