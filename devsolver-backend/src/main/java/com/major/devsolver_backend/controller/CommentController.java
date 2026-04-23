package com.major.devsolver_backend.controller;

import com.major.devsolver_backend.dto.CommentRequest;
import com.major.devsolver_backend.dto.CommentResponse;
import com.major.devsolver_backend.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // Create comment
    @PostMapping
    public ResponseEntity<CommentResponse> createComment(
            @PathVariable Long postId,
            @Valid @RequestBody CommentRequest dto
            ){
        return ResponseEntity.ok(commentService.createComment(postId, dto));
    }

    // Get comments (paginated)
    @GetMapping
    public ResponseEntity<Page<CommentResponse>> getComments(
            @PathVariable Long postId,
            @PageableDefault(
                    size = 10,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC
            ) Pageable pageable
    ){
        return ResponseEntity.ok(commentService.getCommentsByPost(postId, pageable));
    }

    // delete comment (with ownership + validation)
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable Long postId,
            @PathVariable Long commentId
    ){
        commentService.deleteComment(postId, commentId);
        return ResponseEntity.ok("Comment deleted!");
    }
}
