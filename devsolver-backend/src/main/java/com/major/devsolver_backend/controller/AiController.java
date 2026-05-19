package com.major.devsolver_backend.controller;

import com.major.devsolver_backend.dto.AiTagResponse;
import com.major.devsolver_backend.service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiController {

    private final AiService aiService;

    /**
     * Suggest Tags
     */
    @PostMapping("/suggest-tags")
    public ResponseEntity<AiTagResponse> suggestTags(
            @RequestBody String content
    ){
        return ResponseEntity.ok(aiService.suggestTags(content));
    }
}
