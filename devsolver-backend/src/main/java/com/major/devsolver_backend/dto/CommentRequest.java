package com.major.devsolver_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CommentRequest(

        @NotBlank(message = "Comment content is required")
        @Size(max = 1000, message = "Comment cannot exceed 1000")
        String content
) {
}
