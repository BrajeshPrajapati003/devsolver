package com.major.devsolver_backend.service;

import com.major.devsolver_backend.dto.CommentRequest;
import com.major.devsolver_backend.dto.CommentResponse;
import com.major.devsolver_backend.entity.Comment;
import com.major.devsolver_backend.entity.Post;
import com.major.devsolver_backend.entity.User;
import com.major.devsolver_backend.exception.NotFoundException;
import com.major.devsolver_backend.exception.UnauthorizedException;
import com.major.devsolver_backend.repository.CommentRepository;
import com.major.devsolver_backend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    // Create comment
    public CommentResponse createComment(Long postId, CommentRequest dto){

        User user = getAuthenticatedUser();

        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new NotFoundException("Post not found!"));

        Comment comment = Comment.builder()
                .content(dto.content())
                .user(user)
                .post(post)
                .build();

        Comment saved = commentRepository.save(comment);

        return mapToCommentResponse(saved);
    }

    // Delete comment
    public void deleteComment(Long postId, Long commentId){

        User user = getAuthenticatedUser();

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new NotFoundException("Comment not found!"));

        // ensure comment belongs to post
        if (!comment.getPost().getId().equals(postId)){
            throw new RuntimeException("Comment does not belong to this post!");
        }

        // ownership check
        if (!comment.getUser().getId().equals(user.getId())){
            throw new UnauthorizedException("You cannot delete this comment!");
        }

        commentRepository.save(comment);
    }

    // Get comments for post
    public Page<CommentResponse> getCommentsByPost(Long postId, Pageable pageable){

        return commentRepository.findByPostId(postId, pageable)
                .map(this::mapToCommentResponse);
    }

    private CommentResponse mapToCommentResponse(Comment comment){
        return CommentResponse.builder()
                .id(comment.getId())
                .author(comment.getUser().getUsername())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .postId(comment.getPost().getId())
                .build();
    }

    private User getAuthenticatedUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getPrincipal().equals("anonymousUser")) {
            throw new RuntimeException("User not authenticated");
        }

        return (User) auth.getPrincipal();
    }
}
