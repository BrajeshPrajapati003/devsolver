package com.major.devsolver_backend.controller;

import com.major.devsolver_backend.entity.enums.VoteType;
import com.major.devsolver_backend.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts/{postId}/vote")
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping
    public ResponseEntity<String> vote(
            @PathVariable Long postId,
            @RequestParam VoteType type
            ){
        return ResponseEntity.ok(voteService.vote(postId, type));
    }

}
