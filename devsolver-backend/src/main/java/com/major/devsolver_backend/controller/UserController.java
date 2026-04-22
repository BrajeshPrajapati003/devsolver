package com.major.devsolver_backend.controller;

import com.major.devsolver_backend.dto.PostResponse;
import com.major.devsolver_backend.dto.UserRequest;
import com.major.devsolver_backend.dto.UserResponse;
import com.major.devsolver_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Get current user
    @GetMapping("/me")
    public ResponseEntity<UserResponse> getMe(){
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    // Update profile
    @PutMapping("/me")
    public ResponseEntity<UserResponse> updateProfile(
            @RequestBody UserRequest dto
            ){
        return ResponseEntity.ok(userService.updateProfile(dto));
    }

    // get user by id (public)
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // get posts by user id
    @GetMapping("/{id}/posts")
    public ResponseEntity<List<PostResponse>> getPostsByUserId(@PathVariable Long id){
        return ResponseEntity.ok(userService.getPostsByUserId(id));
    }

    // get current user posts
    @GetMapping("/me/posts")
    public ResponseEntity<List<PostResponse>> getMyPosts(){
        return ResponseEntity.ok(userService.getMyPosts());
    }
}
