package com.major.devsolver_backend.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class PostResponse {

    private Long id;
    private String title;
    private String content;
    private String author;
    private Set<String> tags;

    private Long upvotes;
    private Long downvotes;

    private String aiSummary;
}
