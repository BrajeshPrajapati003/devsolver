package com.major.devsolver_backend.service;

import com.major.devsolver_backend.dto.DownloadFile;
import com.major.devsolver_backend.entity.Post;
import com.major.devsolver_backend.entity.Tag;
import com.major.devsolver_backend.exception.NotFoundException;
import com.major.devsolver_backend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostDownloadService {

    private final PostRepository postRepository;


    // Download markdown
    public DownloadFile downloadMarkdown(Long postId){

        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new NotFoundException("Post not found!"));

        String markdown = buildMarkdown(post);

        return new DownloadFile(
                "post-" + post.getId() + ".md",
                MediaType.parseMediaType("text/markdown"),
                markdown.getBytes(StandardCharsets.UTF_8)
        );
    }

    // Markdown Builder
    private String buildMarkdown(Post post){

        String tags = post.getTags()
                .stream()
                .map(Tag::getName)
                .collect(Collectors.joining(", "));

        return """
                # %s
                
                %s
                
                ## Author
                %s
                
                ## Tags
                %s
                
                ## AI Summary
                %s
                """.formatted(
                post.getTitle(),
                post.getContent(),
                post.getUser().getUsername(),
                tags,
                post.getAiSummary()
        );
    }
}
