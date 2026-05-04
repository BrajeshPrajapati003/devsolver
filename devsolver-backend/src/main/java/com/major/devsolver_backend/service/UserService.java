package com.major.devsolver_backend.service;

import com.major.devsolver_backend.dto.PostResponse;
import com.major.devsolver_backend.dto.UserRequest;
import com.major.devsolver_backend.dto.UserResponse;
import com.major.devsolver_backend.entity.Post;
import com.major.devsolver_backend.entity.User;
import com.major.devsolver_backend.exception.FileUploadException;
import com.major.devsolver_backend.exception.NotFoundException;
import com.major.devsolver_backend.repository.PostRepository;
import com.major.devsolver_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public class UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    // current user
    public UserResponse getCurrentUser() {

        return mapToUserResponse(getAuthenticatedUser());
    }

    // update profile
    public UserResponse updateProfile(UserRequest dto){

        User user = getAuthenticatedUser();

        if (dto.username() != null){
            user.setUsername(dto.username());
        }
        if (dto.bio() != null){
            user.setBio(dto.bio());
        }

        return mapToUserResponse(userRepository.save(user));
    }

    // get user by id
    public UserResponse getUserById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("User not found!"));

        return mapToUserResponse(user);
    }


    // posts by user id
    public Page<PostResponse> getPostsByUserId(Long userId, Pageable pageable){

        userRepository.findById(userId)
                .orElseThrow(()-> new NotFoundException("User not found!"));

        return postRepository.findByUserId(userId, pageable)
                .map(this::mapToPostResponse);
    }

    // my posts
    public Page<PostResponse> getMyPosts(Pageable pageable){

        User user = getAuthenticatedUser();

        return postRepository.findByUserId(user.getId(), pageable)
                .map(this::mapToPostResponse);
    }

    public String uploadAvatar(MultipartFile file) {

        try {
            if (file.isEmpty()) throw new FileUploadException("File is empty");

            String ext = Optional.ofNullable(file.getOriginalFilename())
                    .filter(name -> name.contains("."))
                    .map(name -> name.substring(name.lastIndexOf(".")))
                    .orElse(".png");

            String filename = UUID.randomUUID() + ext;

            Path uploadDir = Paths.get("uploads/avatars");
            Files.createDirectories(uploadDir);

            Path filePath = uploadDir.resolve(filename);

            try (InputStream in = file.getInputStream()) {
                Files.copy(in, filePath, StandardCopyOption.REPLACE_EXISTING);
            }

            User user = getAuthenticatedUser();
            user.setAvatarUrl("/uploads/avatars/" + filename);
            userRepository.save(user);

            return user.getAvatarUrl();

        } catch (IOException e) {
            throw new FileUploadException("Failed to upload avatar");
        }
    }

    //---------- helpers ---------------
    private UserResponse mapToUserResponse(User user){

        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .bio(user.getBio())
                .build();
    }

    private PostResponse mapToPostResponse(Post post) {

        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getUser().getUsername())
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
