package com.major.devsolver_backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

//    private String username;
//    private String email;
    private String message;
    private String token;
    private UserResponse user;
    // using nested design:
        // cleaner structure,
        // easier to extend (bio, avatar later),
        // industry standard
}
