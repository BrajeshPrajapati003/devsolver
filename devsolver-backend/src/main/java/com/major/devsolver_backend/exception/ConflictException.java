package com.major.devsolver_backend.exception;

public class ConflictException extends RuntimeException { // status = 409
    public ConflictException(String message) {
        super(message);
    }
}
