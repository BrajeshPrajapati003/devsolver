package com.major.devsolver_backend.controller;

import com.major.devsolver_backend.dto.PostRequest;
import com.major.devsolver_backend.dto.PostResponse;
import com.major.devsolver_backend.entity.Post;
import com.major.devsolver_backend.entity.User;
import com.major.devsolver_backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

//    @PostMapping
//    public ResponseEntity<PostResponse> createPost(
//            @RequestBody PostRequest dto,
//            Principal principal
//            ){
//
//        return ResponseEntity.ok(
//                postService.createPost(dto, principal.getName())
//        );
//    }

    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest dto){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        return ResponseEntity.ok(postService.createPost(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> updatePost(
            @PathVariable Long id,
            @RequestBody PostRequest dto
    ){
        return ResponseEntity.ok(postService.updatePost(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id){
        postService.deletePost(id);
        return ResponseEntity.ok("Post deleted successfully!");
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPost(){
        return ResponseEntity.ok(postService.getAllPosts());
    }
}
