package com.major.devsolver_backend.dto;

import jakarta.validation.constraints.Size;

import java.util.Set;

public record DraftRequest(

        Long draftId,

        @Size(max = 150, message = "Title cannot exceed 150 characters")
        String title,
        String content,
        Set<String> tags
) {
}
