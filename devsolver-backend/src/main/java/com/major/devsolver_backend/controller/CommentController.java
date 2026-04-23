package com.major.devsolver_backend.controller;

import com.major.devsolver_backend.dto.CommentRequest;
import com.major.devsolver_backend.dto.CommentResponse;
import com.major.devsolver_backend.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {

//    POST   /api/posts/{postId}/comments
//    GET    /api/posts/{postId}/comments
//    DELETE /api/comments/{id}

    private final CommentService commentService;

    // Create comment
    @PostMapping
    public ResponseEntity<CommentResponse> createComment(
            @PathVariable Long postId,
            @RequestBody CommentRequest dto
            ){
        return ResponseEntity.ok(commentService.createComment(postId, dto));
    }

    // Get comments
    @GetMapping
    public ResponseEntity<List<CommentResponse>> getComments(
            @PathVariable Long postId
    ){
        return ResponseEntity.ok(commentService.getCommentsByPost(postId));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable Long postId,
            @PathVariable Long commentId
    ){
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("Comment deleted!");
    }
}
