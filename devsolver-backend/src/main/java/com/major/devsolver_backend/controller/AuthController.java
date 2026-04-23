package com.major.devsolver_backend.controller;

import com.major.devsolver_backend.dto.*;
import com.major.devsolver_backend.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // never return ResponseEntity<User> because you might expose sensitive data
    // even hashed password should never be exposed
    // Tight coupling -> you are exposing your internal entity structure directly.
    // If you change db fields -> api breaks
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
            @Valid @RequestBody RegisterRequest req
    ){

        return ResponseEntity.ok(authService.register(req));
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request
    ){

        return ResponseEntity.ok(authService.login(request));
    }
 }
