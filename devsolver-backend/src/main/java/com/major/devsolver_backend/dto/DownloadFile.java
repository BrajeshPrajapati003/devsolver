package com.major.devsolver_backend.dto;

import org.springframework.http.MediaType;

public record DownloadFile(
        String filename,
        MediaType mediaType,
        byte[] content
) {
}
