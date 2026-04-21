package com.major.devsolver_backend.service;

import com.major.devsolver_backend.dto.PostRequest;
import com.major.devsolver_backend.dto.PostResponse;
import com.major.devsolver_backend.entity.Post;
import com.major.devsolver_backend.entity.User;
import com.major.devsolver_backend.repository.PostRepository;
import com.major.devsolver_backend.repository.TagRepository;
import com.major.devsolver_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

//    createPost()
//    updatePost()
//    deletePost()
//    getPostById()
//    getAllPosts()
//    searchPosts()


    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;


    // Create post
    public PostResponse createPost(PostRequest dto, String userEmail){

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(()-> new RuntimeException("User not found!"));

        Post post = mapToPost(dto, user);
        Post savedPost = postRepository.save(post);

        return mapToPostResponse(savedPost);
    }


    // Get all posts
    public List<PostResponse> getAllPosts(){
        return postRepository.findAll().stream()
                .map(this::mapToPostResponse).toList();
    }



    // Private helper
    private Post mapToPost(PostRequest request, User user){
        return Post.builder()
                .title(request.title())
                .content(request.content())
                .user(user)
                .build();
    }

    // private helper
    private PostResponse mapToPostResponse(Post post){
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getUser().getUsername())
                // .tags(...) -> add later when relation is implemented
                .build();
    }
}
