package com.major.devsolver_backend.exception;

public class UnauthorizedException extends RuntimeException { // status = 403
    public UnauthorizedException(String message) {
        super(message);
    }
}
