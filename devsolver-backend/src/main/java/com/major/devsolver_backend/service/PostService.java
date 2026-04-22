package com.major.devsolver_backend.service;

import com.major.devsolver_backend.dto.PostRequest;
import com.major.devsolver_backend.dto.PostResponse;
import com.major.devsolver_backend.entity.Post;
import com.major.devsolver_backend.entity.User;
import com.major.devsolver_backend.exception.NotFoundException;
import com.major.devsolver_backend.exception.UnauthorizedException;
import com.major.devsolver_backend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // Create post
    public PostResponse createPost(PostRequest dto){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        Post post = mapToPost(dto, user);
        Post savedPost = postRepository.save(post);

        return mapToPostResponse(savedPost);
    }


    // Only author can update/delete post

    // update post
    public PostResponse updatePost(Long postId, PostRequest dto){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new NotFoundException("Post not found!"));

        // ownership check
        if (!post.getUser().getId().equals(user.getId())){
            throw new UnauthorizedException("Unauthorized Operation done...");
        }

        // update fields (only if provided)
        if (dto.title() != null){
            post.setTitle(dto.title());
        }

        if (dto.content() != null){
            post.setContent(dto.content());
        }

        // TODO: tags later when implemented properly

        Post updatedPost = postRepository.save(post);

        return mapToPostResponse(updatedPost);
    }


    // Delete post
    public void deletePost(Long postId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new NotFoundException("Post not found!"));

        // ownership check
        if (!post.getUser().getId().equals(user.getId())){
            throw new UnauthorizedException("You're not allowed to delete this post!");
        }

        postRepository.delete(post);
    }

    // Get post by post id
    public PostResponse getPostById(Long postId){

        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new NotFoundException("Post not found!"));

        return mapToPostResponse(post);
    }


    // Get all posts
//    public List<PostResponse> getAllPosts(){
//        return postRepository.findAll().stream()
//                .map(this::mapToPostResponse).toList();
//    }

    // Get all posts with pagination
    public Page<PostResponse> getAllPosts(Pageable pageable){

        return postRepository.findAll(pageable)
                .map(this::mapToPostResponse);
    }

    // Get posts by user id (filter use-case)
    public List<PostResponse> getPostsByUserId(Long userId){

        return postRepository.findByUserId(userId)
                .stream()
                .map(this::mapToPostResponse)
                .toList();
    }


    // Private helper
    private Post mapToPost(PostRequest request, User user){
        return Post.builder()
                .title(request.title())
                .content(request.content())
                .user(user)
                .build();
    }

    // private helper
    private PostResponse mapToPostResponse(Post post){
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getUser().getUsername())
                // .tags(...) -> add later when relation is implemented
                .build();
    }
}
