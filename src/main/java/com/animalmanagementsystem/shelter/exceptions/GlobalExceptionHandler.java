package com.animalmanagementsystem.shelter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AnimalNotFoundException.class)
    ResponseEntity<Object> handleAnimalNotFoundException(AnimalNotFoundException exception) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_FOUND, exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(response, response.status());
    }

    @ExceptionHandler(CageNotFoundException.class)
    ResponseEntity<Object> handleCageNotFoundException(CageNotFoundException exception) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_FOUND, exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(response, response.status());
    }

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_FOUND, exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(response, response.status());
    }

    @ExceptionHandler(HealthNotFoundException.class)
    ResponseEntity<Object> handleHealthNotFoundException(HealthNotFoundException exception) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_FOUND, exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(response, response.status());
    }

    @ExceptionHandler(RoleNotFoundException.class)
    ResponseEntity<Object> handleRoleNotFoundException(RoleNotFoundException exception) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_FOUND, exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(response, response.status());
    }

    @ExceptionHandler(UserRoleNotFoundException.class)
    ResponseEntity<Object> handleUserRoleNotFoundException(UserRoleNotFoundException exception) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_FOUND, exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(response, response.status());
    }

    @ExceptionHandler(UserAnimalNotFoundException.class)
    ResponseEntity<Object> handleUserAnimalNotFoundException(UserAnimalNotFoundException exception) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_FOUND, exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(response, response.status());
    }
    
}
