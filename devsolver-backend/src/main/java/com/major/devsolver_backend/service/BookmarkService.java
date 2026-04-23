package com.major.devsolver_backend.service;

import com.major.devsolver_backend.dto.PostResponse;
import com.major.devsolver_backend.entity.Bookmark;
import com.major.devsolver_backend.entity.Post;
import com.major.devsolver_backend.entity.User;
import com.major.devsolver_backend.exception.NotFoundException;
import com.major.devsolver_backend.repository.BookmarkRepository;
import com.major.devsolver_backend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final PostRepository postRepository;
    private final PostService postService;

    // Toggle Bookmark
    public String toggleBookmark(Long postId){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

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

    // Get all bookmarks
    public List<PostResponse> getMyBookmarks(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        return bookmarkRepository.findByUser(user)
                .stream()
                .map(b -> postService.getPostById(b.getPost().getId()))
                .toList();
    }
}
