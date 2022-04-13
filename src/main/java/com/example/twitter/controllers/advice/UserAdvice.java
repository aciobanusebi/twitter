package com.example.twitter.controllers.advice;

import com.example.twitter.data.entities.Follow;
import com.example.twitter.services.exceptions.FollowAlreadyExistsException;
import com.example.twitter.services.exceptions.UserAlreadyExistsException;
import com.example.twitter.services.exceptions.UserNotFoundException;
import com.example.twitter.services.exceptions.UserSearchImpossibleException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserAdvice {

    @ResponseBody
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String userAlreadyExistsHandler(UserAlreadyExistsException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String userNotFoundHandler(UserNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UserSearchImpossibleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String userSearchImpossibleHandler(UserSearchImpossibleException ex) {
        return ex.getMessage();
    }
}

