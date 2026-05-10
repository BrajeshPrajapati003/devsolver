package com.major.devsolver_backend.dto;

import lombok.Builder;

import java.time.Instant;
import java.util.Set;

@Builder
public record DraftResponse(
        Long id,
        String title,
        String content,
        Set<String> tags,
        Instant updatedAt
) {
}
