package com.major.devsolver_backend.dto;

public record RegisterRequest(
        String username,
        String email,
        String password
) {
}
