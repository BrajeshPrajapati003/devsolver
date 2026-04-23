package com.major.devsolver_backend.controller;

import com.major.devsolver_backend.dto.PostResponse;
import com.major.devsolver_backend.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    // Toggle Bookmark
    @PostMapping("/posts/{postId}/bookmark")
    public ResponseEntity<String> toggleBookmark(@PathVariable Long postId){

        return ResponseEntity.ok(bookmarkService.toggleBookmark(postId));
    }

    // my bookmarks
    @GetMapping("/users/me/bookmarks")
    public ResponseEntity<Page<PostResponse>> getMyBookmarks(Pageable pageable){

        return ResponseEntity.ok(bookmarkService.getMyBookmarks(pageable));
    }
}
