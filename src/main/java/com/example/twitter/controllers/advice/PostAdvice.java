package com.example.twitter.controllers.advice;

import com.example.twitter.services.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class PostAdvice {
    @ResponseBody
    @ExceptionHandler(PostAddImpossibleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String postAddImpossibleHandler(PostAddImpossibleException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(PostGetImpossibleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String postGetImpossibleHandler(PostGetImpossibleException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(PostNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String postGetImpossibleHandler(PostNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
