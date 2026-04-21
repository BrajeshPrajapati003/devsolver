package com.major.devsolver_backend.controller;

import com.major.devsolver_backend.dto.LoginRequest;
import com.major.devsolver_backend.dto.LoginResponse;
import com.major.devsolver_backend.dto.RegisterRequest;
import com.major.devsolver_backend.dto.UserResponse;
import com.major.devsolver_backend.entity.User;
import com.major.devsolver_backend.service.AuthService;
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

    // register endpoint - POST

    // login endpoint - POST

    // me endpoint - GET

    private final AuthService authService;

    // never return ResponseEntity<User> because you might expose sensitive data
    // even hashed password should never be exposed
    // Tight coupling -> you are exposing your internal entity structure directly.
    // If you change db fields -> api breaks
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @RequestBody RegisterRequest req
    ){
        User savedUser = authService.register(req);

        UserResponse res = UserResponse.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .build();

        return ResponseEntity.ok(res);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request
    ){
        User user = authService.login(request);

        LoginResponse res = LoginResponse.builder()
                .message("Login successful")
                .username(user.getUsername())
                .email(user.getEmail())
                .build();

        return ResponseEntity.ok(res);
    }
 }
