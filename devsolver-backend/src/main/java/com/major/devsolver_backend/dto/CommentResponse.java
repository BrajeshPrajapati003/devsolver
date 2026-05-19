package com.major.devsolver_backend.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Builder
@Data
public class CommentResponse {

    private Long id;
    private String content;
    private String author;

    // optional upgrades
    private Instant createdAt;
    private Long postId;

}

/*
- Records become painful if the ResponseDTOs are huge
- Builder becomes much cleaner

Request and Response both should be treated as IMMUTABLE API CONTRACTS
hence both should be record instead of Builder Classes.
 */