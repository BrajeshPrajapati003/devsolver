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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public UserResponse getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        return mapToUserResponse(user);
    }

    public UserResponse updateProfile(UserRequest dto){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        if (dto.username() != null){
            user.setUsername(dto.username());
        }

        if (dto.bio() != null){
            user.setBio(dto.bio());
        }

        User updatedUser = userRepository.save(user);
        return mapToUserResponse(updatedUser);
    }

    // get user by id
    public UserResponse getUserById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("User not found!"));

        return mapToUserResponse(user);
    }


    // get posts by id
    public List<PostResponse> getPostsByUserId(Long userId){
        return postRepository.findByUserId(userId)
                .stream()
                .map(this::mapToPostResponse)
                .toList();
    }

    // get current user posts
    public List<PostResponse> getMyPosts(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        return postRepository.findByUserId(user.getId())
                .stream()
                .map(this::mapToPostResponse)
                .toList();
    }

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
}
