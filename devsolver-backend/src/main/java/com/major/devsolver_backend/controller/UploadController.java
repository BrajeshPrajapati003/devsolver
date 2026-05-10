package com.major.devsolver_backend.controller;

import com.major.devsolver_backend.dto.UploadResponse;
import com.major.devsolver_backend.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/uploads")
@RequiredArgsConstructor
public class UploadController {

    private final UploadService uploadService;

    // Avatar upload
    @PostMapping("/avatar")
    public ResponseEntity<UploadResponse> uploadAvatar(
            @RequestParam("file") MultipartFile file
            ){
        return ResponseEntity.ok(uploadService.uploadAvatar(file));
    }

    // Post image upload
    @PostMapping("/post-image")
    public ResponseEntity<UploadResponse> uploadPostImage(
            @RequestParam("file") MultipartFile file
    ){
        return ResponseEntity.ok(uploadService.uploadPostImage(file));
    }
}
