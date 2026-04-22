package com.major.devsolver_backend.controller;

import com.major.devsolver_backend.dto.PostRequest;
import com.major.devsolver_backend.dto.PostResponse;
import com.major.devsolver_backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // create post
    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest dto){
        return ResponseEntity.ok(postService.createPost(dto));
    }

    // get all posts
    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts(){
        return ResponseEntity.ok(postService.getAllPosts());
    }

    // get post by id
    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    // update post (only owner)
    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> updatePost(
            @PathVariable Long id,
            @RequestBody PostRequest dto
    ){
        return ResponseEntity.ok(postService.updatePost(id, dto));
    }

    // delete post (only owner)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id){
        postService.deletePost(id);
        return ResponseEntity.ok("Post deleted successfully!");
    }

    // filter posts by user (scalable)
    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<PostResponse>> getPostsByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(postService.getPostsByUserId(userId));
    }

    // filtering + search
//    GET /api/posts?tag=SpringBoot
//    GET /api/posts?search=jwt+error
//    GET /api/posts?sort=top
//    GET /api/posts?page=1&size=10
}
