package com.major.devsolver_backend.dto;

import java.util.List;

public record PostRequest(
        String title,
        String content,
        List<String> tags
) {
}
