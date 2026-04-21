package com.major.devsolver_backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

    private String message;
    private String username;
    private String email;
    private String token; // future use (JWT)
}
