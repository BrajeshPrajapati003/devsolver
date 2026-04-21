package com.major.devsolver_backend.controller;

import com.major.devsolver_backend.dto.PostRequest;
import com.major.devsolver_backend.dto.PostResponse;
import com.major.devsolver_backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    // posts - POST
    // posts - GET
    // through id - GET
    // through id - PUT
    // through id - DELETE


    // filtering + search
//    GET /api/posts?tag=SpringBoot
//    GET /api/posts?search=jwt+error
//    GET /api/posts?sort=top
//    GET /api/posts?page=1&size=10


    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponse> createPost(
            @RequestBody PostRequest dto,
            Principal principal
            ){

        return ResponseEntity.ok(
                postService.createPost(dto, principal.getName())
        );
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPost(){
        return ResponseEntity.ok(postService.getAllPosts());
    }
}
