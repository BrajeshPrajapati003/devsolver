package com.major.devsolver_backend.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFound(NotFoundException ex){

        return ResponseEntity.status(404).body(
                Map.of("error", ex.getMessage())
        );
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> handleUnauthorized(UnauthorizedException ex){

        return ResponseEntity.status(403).body(
                Map.of("error", ex.getMessage())
        );
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<?> handleConflict(ConflictException ex){

        return ResponseEntity.status(409).body(
                Map.of("error", ex.getMessage())
        );
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<?> handleInvalidCredentials(InvalidCredentialsException ex){

        return ResponseEntity.status(401).body(
                Map.of("error", ex.getMessage())
        );
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<?> handleMissingPathVariable(MissingPathVariableException ex){

        return ResponseEntity.badRequest().body("Missing parameter!");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneral(Exception ex){

        ex.printStackTrace();
        return ResponseEntity.status(500).body(
                Map.of("error", "Something went wrong")
        );
    }

//    @ExceptionHandler(Exception.class)
//    public void handleGeneralExceptionForTesting(Exception ex){
//        ex.printStackTrace();
//    }
}
