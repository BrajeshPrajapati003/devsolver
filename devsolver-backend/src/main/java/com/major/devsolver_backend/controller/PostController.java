package com.major.devsolver_backend.controller;

import com.major.devsolver_backend.dto.DownloadFile;
import com.major.devsolver_backend.dto.PostRequest;
import com.major.devsolver_backend.dto.PostResponse;
import com.major.devsolver_backend.service.PostDownloadService;
import com.major.devsolver_backend.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostDownloadService postDownloadService;

    // create
    @PostMapping
    public ResponseEntity<PostResponse> createPost(@Valid @RequestBody PostRequest dto){
        return ResponseEntity.ok(postService.createPost(dto));
    }


    // Get (pagination + filtering)
    @GetMapping
    public ResponseEntity<Page<PostResponse>> getPosts(
            @RequestParam(required = false) Long userId, // filter by user id
            @RequestParam(required = false) String tag, // filter by tag
            @PageableDefault(
                    size = 10,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC
            ) Pageable pageable
    ){

        if (userId != null){
            return ResponseEntity.ok(postService.getPostsByUserId(userId, pageable));
        }

        if (tag != null){
            return ResponseEntity.ok(postService.getPostsByTag(tag, pageable));
        }

        return ResponseEntity.ok(postService.getAllPosts(pageable));
    }

    // get by id
    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    // update (only owner)
    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> updatePost(
            @PathVariable Long id,
            @Valid @RequestBody PostRequest dto
    ){
        return ResponseEntity.ok(postService.updatePost(id, dto));
    }

    // delete (only owner)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id){
        postService.deletePost(id);
        return ResponseEntity.ok("Post deleted successfully!");
    }

    // Download post
    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadPost(
            @PathVariable Long id
    ){
        DownloadFile file = postDownloadService.downloadMarkdown(id);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=" + file.filename()
                )
                .contentType(file.mediaType())
                .body(file.content());
    }


    // downloadPDF(), downloadHTML(), downloadDocx() -->> later in ExportService
}
