package com.raymond.baristamatic.controller;

import com.raymond.baristamatic.exception.DrinkNotFoundException;
import com.raymond.baristamatic.exception.InsufficientIngredientException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(DrinkNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,String> handleDrinkNotFound() {
        Map<String,String> response = new HashMap<>();
        response.put("message","Drink not found. Please see /menu");
        return response;
    }

    @ExceptionHandler(InsufficientIngredientException.class)
    @ResponseStatus(HttpStatus.OK)
    public Map<String,String> handleInsufficientIngredient(InsufficientIngredientException e) {
        Map<String,String> response = new HashMap<>();
        response.put("message","Insufficient ingredients: " + e.getMessage() + ". Please /restock");
        return response;
    }

    @ExceptionHandler(ServletException.class)
    public void handleServletException(ServletException e) throws ServletException {
        throw e;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,String> handleUnknown(Exception e) {
        Map<String,String> response = new HashMap<>();
        response.put("message","Unknown exception. " + e.getMessage());
        return response;
    }
}
