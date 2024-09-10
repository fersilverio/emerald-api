package com.obsidian.emeraldapi.infrastructure.security.exceptions.authentication;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthExceptionHandler {
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentials(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<String> handleDisabledAccount(DisabledException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Account is disabled");
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<String> handleLockedAccount(LockedException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Account is locked");
    }

    @ExceptionHandler(AccountExpiredException.class)
    public ResponseEntity<String> handleExpiredAccount(AccountExpiredException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Account has expired");
    }

    @ExceptionHandler(CredentialsExpiredException.class)
    public ResponseEntity<String> handleExpiredCredentials(CredentialsExpiredException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credentials have expired");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
    }
}
