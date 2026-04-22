package com.major.devsolver_backend.dto;

import jakarta.validation.constraints.Size;

public record UserRequest(

        @Size(min = 3, max = 20) String username,
        @Size(max = 200) String bio
) {
}
