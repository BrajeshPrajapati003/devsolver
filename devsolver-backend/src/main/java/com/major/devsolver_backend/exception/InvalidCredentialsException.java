package com.major.devsolver_backend.exception;

public class InvalidCredentialsException extends RuntimeException { // status = 401
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
