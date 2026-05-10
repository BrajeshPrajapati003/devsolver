package com.major.devsolver_backend.dto;

import java.util.Set;

public record DraftRequest(

        Long draftId,
        String title,
        String content,
        Set<String> tags
) {
}
