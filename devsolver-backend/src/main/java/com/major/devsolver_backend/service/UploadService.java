package com.major.devsolver_backend.service;

import com.major.devsolver_backend.dto.UploadResponse;
import com.major.devsolver_backend.entity.User;
import com.major.devsolver_backend.exception.FileUploadException;
import com.major.devsolver_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UploadService {

    private final UserRepository userRepository;

    // Avatar upload
    public UploadResponse uploadAvatar(MultipartFile file){

        String url = upload(file, "avatars");
        User user = getAuthenticatedUser();
        user.setAvatarUrl(url);
        userRepository.save(user);

        return UploadResponse.builder()
                .url(url)
                .build();
    }

    // Post image upload
    public UploadResponse uploadPostImage(MultipartFile file){

        String url = upload(file, "post-image");

        return UploadResponse.builder()
                .url(url)
                .build();
    }

    // Shared upload logic
    private String upload(MultipartFile file, String folderName){
        try{
            validateFile(file);
            String filename = generateFilename(file);
            Path uploadPath = Paths.get("uploads", folderName);

            Files.createDirectories(uploadPath);

            Path filePath = uploadPath.resolve(filename);

            try (InputStream inputStream = file.getInputStream()){

                Files.copy(
                        inputStream,
                        filePath,
                        StandardCopyOption.REPLACE_EXISTING
                );
            }

            return "/uploads/" + folderName + "/" + filename;
        }catch (IOException e){
            throw new FileUploadException("Failed to upload file");
        }
    }

    // File validation
    private void validateFile(MultipartFile file){

        if (file.isEmpty()){
            throw new FileUploadException("File is empty");
        }

        if (file.getSize() > 5 * 1024 * 1024){
            throw new FileUploadException("File size exceeds 5MB");
        }

        String contentType = file.getContentType();

        if (contentType == null || !contentType.startsWith("image/")){
            throw new FileUploadException("Only image files are allowed");
        }
    }

    // Unique Filename Generator
    private String generateFilename(MultipartFile file){

        String original = file.getOriginalFilename();
        String extension = Optional.ofNullable(original)
                .filter(name -> name.contains("."))
                .map(name -> name.substring(name.lastIndexOf(".")))
                .orElse(".png");

        return UUID.randomUUID() + extension;
    }

    // Authenticated User
    private User getAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}
