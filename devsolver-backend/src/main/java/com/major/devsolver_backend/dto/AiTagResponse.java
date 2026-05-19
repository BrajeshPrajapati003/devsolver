package com.major.devsolver_backend.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class AiTagResponse {

    Set<String> tags;
}
