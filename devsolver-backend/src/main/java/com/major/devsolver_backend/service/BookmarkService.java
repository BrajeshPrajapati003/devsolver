package com.major.devsolver_backend.service;

import com.major.devsolver_backend.dto.PostResponse;
import com.major.devsolver_backend.entity.Bookmark;
import com.major.devsolver_backend.entity.Post;
import com.major.devsolver_backend.entity.User;
import com.major.devsolver_backend.exception.NotFoundException;
import com.major.devsolver_backend.repository.BookmarkRepository;
import com.major.devsolver_backend.repository.PostRepository;
import com.major.devsolver_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // Toggle Bookmark
    public String toggleBookmark(Long postId){

        User user = getAuthenticatedUser();

        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new NotFoundException("Post not found"));

        var existing = bookmarkRepository.findByUserAndPost(user, post);

        if (existing.isPresent()){
            bookmarkRepository.delete(existing.get());
            return "Bookmark removed!";
        }

        Bookmark bookmark = Bookmark.builder()
                .user(user)
                .post(post)
                .build();

        bookmarkRepository.save(bookmark);
        return "Bookmarked!";
    }

    // Get bookmarks (Paginated)
    public Page<PostResponse> getMyBookmarks(Pageable pageable){

        User user = getAuthenticatedUser();

        return bookmarkRepository.findByUser(user, pageable)
                .map(bookmark -> mapToPostResponse(bookmark.getPost()));
    }


    // --------------helpers------------
    private PostResponse mapToPostResponse(Post post){
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .author(post.getUser().getUsername())
                .content(post.getContent())
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
