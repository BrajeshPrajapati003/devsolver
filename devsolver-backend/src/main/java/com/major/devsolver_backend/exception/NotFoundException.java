package com.major.devsolver_backend.exception;

public class NotFoundException extends RuntimeException { // status = 404
    public NotFoundException(String message) {
        super(message);
    }
}
