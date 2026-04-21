package com.major.devsolver_backend.dto;

public record LoginRequest(
        String email,
        String password
) {
}
