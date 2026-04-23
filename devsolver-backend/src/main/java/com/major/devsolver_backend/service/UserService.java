package com.major.devsolver_backend.service;

import com.major.devsolver_backend.dto.PostResponse;
import com.major.devsolver_backend.dto.UserRequest;
import com.major.devsolver_backend.dto.UserResponse;
import com.major.devsolver_backend.entity.Post;
import com.major.devsolver_backend.entity.User;
import com.major.devsolver_backend.exception.NotFoundException;
import com.major.devsolver_backend.repository.PostRepository;
import com.major.devsolver_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    //---------- helpers ---------------
    private UserResponse mapToUserResponse(User user){

        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .bio(user.getBio())
                .build();
    }

    private PostResponse mapToPostResponse(Post post){

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
