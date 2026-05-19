package com.major.devsolver_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CommentRequest(

        @NotBlank(message = "Comment content is required")
        @Size(max = 1000, message = "Comment cannot exceed 1000")
        String content
) {
}

/*
REQUEST -> simple immutable input
RESPONSE -> gradually assembled output

- keep request DTOs as records always
- use records for simple response DTOs (like LoginResponse, AiTagResponse, UploadResponse)
- use builder classes for LARGE/COMPLEX responses (like PostDetailResponse, FeedResponse, AnalyticsResponse)
    because builder improves readability
 */