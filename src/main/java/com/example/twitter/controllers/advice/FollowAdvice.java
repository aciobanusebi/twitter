package com.example.twitter.controllers.advice;

import com.example.twitter.services.exceptions.FollowAlreadyExistsException;
import com.example.twitter.services.exceptions.FollowNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class FollowAdvice {
    @ResponseBody
    @ExceptionHandler(FollowAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String followAlreadyExistsHandler(FollowAlreadyExistsException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(FollowNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String followNotFoundHandler(FollowNotFoundException ex) {
        return ex.getMessage();
    }
}
