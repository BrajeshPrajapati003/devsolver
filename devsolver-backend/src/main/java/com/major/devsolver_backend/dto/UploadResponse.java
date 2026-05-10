package com.major.devsolver_backend.dto;

import lombok.Builder;

@Builder
public record UploadResponse(
        String url
) {
}
