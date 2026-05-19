package com.major.devsolver_backend.service;

import com.major.devsolver_backend.dto.PostRequest;
import com.major.devsolver_backend.dto.PostResponse;
import com.major.devsolver_backend.entity.Post;
import com.major.devsolver_backend.entity.Tag;
import com.major.devsolver_backend.entity.User;
import com.major.devsolver_backend.entity.enums.VoteType;
import com.major.devsolver_backend.exception.NotFoundException;
import com.major.devsolver_backend.exception.UnauthorizedException;
import com.major.devsolver_backend.repository.PostRepository;
import com.major.devsolver_backend.repository.TagRepository;
import com.major.devsolver_backend.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final VoteRepository voteRepository;
    private final TagRepository tagRepository;
    private final AiService aiService;

    // Create post
    public PostResponse createPost(PostRequest dto){

        User user = getAuthenticatedUser();

        Set<String> finalTags = dto.tags();

        // AI Fallback if user gives no tags
        if(finalTags == null || finalTags.isEmpty()){

            finalTags = aiService.suggestTags(dto.content()).getTags();
        }

        Set<Tag> tags = finalTags.stream()
                        .map(tagName -> {
                            String normalized = tagName.toLowerCase().trim(); // normalize tags (JAVA = java = JaVa)

                            return tagRepository.findByName(normalized)
                                    .orElseGet(()-> tagRepository.save(
                                            Tag.builder().name(normalized).build()
                                    ));
                        })
                        .collect(Collectors.toSet());

        // AI Summary
        String summary = aiService.generatePostSummary(dto.content());

        // Create Post
        Post post = Post.builder()
                .title(dto.title())
                .content(dto.content())
                .user(user)
                .tags(tags)
                .aiSummary(summary)
                .build();
        Post savedPost = postRepository.save(post);

        return mapToPostResponse(savedPost);
    }


    // update post
    public PostResponse updatePost(Long postId, PostRequest dto){

        User user = getAuthenticatedUser();

        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new NotFoundException("Post not found!"));

        // ownership check
        if (!post.getUser().getId().equals(user.getId())){
            throw new UnauthorizedException("Unauthorized Operation done...");
        }

        // update fields (only if provided)
        if (dto.title() != null){
            post.setTitle(dto.title());
        }

        if (dto.content() != null){
            post.setContent(dto.content());
        }

        if (dto.tags() != null){

            Set<Tag> tags = dto.tags().stream()
                    .map(tagName -> {
                        String normalized = tagName.toLowerCase().trim();

                        return tagRepository.findByName(normalized)
                                .orElseGet(()-> tagRepository.save(
                                        Tag.builder().name(normalized).build()
                                ));
                    })
                    .collect(Collectors.toSet());

            post.setTags(tags);
        }

        post.setAiSummary(aiService.generatePostSummary(dto.content()));

        Post updatedPost = postRepository.save(post);

        return mapToPostResponse(updatedPost);
    }


    // Delete post
    public void deletePost(Long postId){

        User user = getAuthenticatedUser();

        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new NotFoundException("Post not found!"));

        // ownership check
        if (!post.getUser().getId().equals(user.getId())){
            throw new UnauthorizedException("You're not allowed to delete this post!");
        }

        postRepository.delete(post);
    }

    // Get post by post id
    public PostResponse getPostById(Long postId){

        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new NotFoundException("Post not found!"));

        return mapToPostResponse(post);
    }


    // Get all posts with pagination
    public Page<PostResponse> getAllPosts(Pageable pageable){

        return postRepository.findAll(pageable)
                .map(this::mapToPostResponse);
    }

    // Get posts by user id (filter use-case)
    public Page<PostResponse> getPostsByUserId(Long userId, Pageable pageable){

        return postRepository.findByUserId(userId, pageable)
                .map(this::mapToPostResponse);
    }

    // GET posts by tag
    public Page<PostResponse> getPostsByTag(String tag, Pageable pageable){

        return postRepository.findByTags_Name(tag.toLowerCase(), pageable)
                .map(this::mapToPostResponse);
    }

    // private helper
    private PostResponse mapToPostResponse(Post post){

        Long upvotes = voteRepository.countByPostIdAndType(post.getId(), VoteType.UPVOTE);
        Long downvotes = voteRepository.countByPostIdAndType(post.getId(), VoteType.DOWNVOTE);

        // For each post -> 2 DB queries (up + down) = bad for large scale
        // Optimize it with JOIN / aggregation query

        Set<String> tagNames = post.getTags() == null ? Set.of() :
                post.getTags().stream()
                        .map(Tag::getName)
                        .collect(Collectors.toSet());

        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getUser().getUsername())
                .upvotes(upvotes)
                .downvotes(downvotes)
                .tags(tagNames)
                .aiSummary(post.getAiSummary())
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
