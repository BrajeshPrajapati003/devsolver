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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    // Create comment
    public CommentResponse createComment(Long postId, CommentRequest dto){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

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
    public void deleteComment(Long commentId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new NotFoundException("Comment not found!"));

        if (!comment.getUser().getId().equals(user.getId())){
            throw new UnauthorizedException("You cannot delete this comment!");
        }

        commentRepository.save(comment);
    }

    // Get comments for post
    public List<CommentResponse> getCommentsByPost(Long postId){

        return commentRepository.findByPostId(postId)
                .stream()
                .map(this::mapToCommentResponse)
                .toList();
    }

    private CommentResponse mapToCommentResponse(Comment comment){
        return CommentResponse.builder()
                .id(comment.getId())
                .author(comment.getUser().getUsername())
                .content(comment.getContent())
                .build();
    }
}
