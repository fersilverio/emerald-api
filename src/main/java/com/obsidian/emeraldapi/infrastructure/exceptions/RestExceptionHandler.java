package com.obsidian.emeraldapi.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> invalidCredentialsHandler(InvalidCredentialsException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid credentials");
    }

    @ExceptionHandler(EntityCreationFailedException.class)
    public ResponseEntity<String> entityCreationFailedHandler(EntityCreationFailedException  exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fail on creating new entity");
    }
}
