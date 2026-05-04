package com.major.devsolver_backend.controller;

import com.major.devsolver_backend.dto.PostResponse;
import com.major.devsolver_backend.dto.UserRequest;
import com.major.devsolver_backend.dto.UserResponse;
import com.major.devsolver_backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // current user
    @GetMapping("/me")
    public ResponseEntity<UserResponse> getMe(){
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    // Update profile
    @PutMapping("/me")
    public ResponseEntity<UserResponse> updateProfile(
            @Valid @RequestBody UserRequest dto
            ){
        return ResponseEntity.ok(userService.updateProfile(dto));
    }

    // user by id
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // posts by user id
    @GetMapping("/{id}/posts")
    public ResponseEntity<Page<PostResponse>> getPostsByUserId(
            @PathVariable Long id,
            @PageableDefault(
                    size = 10,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC
            )Pageable pageable
            ){
        return ResponseEntity.ok(userService.getPostsByUserId(id, pageable));
    }

    // my posts
    @GetMapping("/me/posts")
    public ResponseEntity<Page<PostResponse>> getMyPosts(
            @PageableDefault(
                    size = 10,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC
            ) Pageable pageable
    ){
        return ResponseEntity.ok(userService.getMyPosts(pageable));
    }

    // Avatar
    @PostMapping("/me/avatar")
    public ResponseEntity<String> uploadAvatar(@RequestParam("file") MultipartFile file){
        return ResponseEntity.ok(userService.updateAvatar(file));
    }
}
