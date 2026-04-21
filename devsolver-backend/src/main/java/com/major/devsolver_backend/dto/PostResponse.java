package com.major.devsolver_backend.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PostResponse {

    private Long id;
    private String title;
    private String content;
    private String author;
    private List<String> tags;

}
