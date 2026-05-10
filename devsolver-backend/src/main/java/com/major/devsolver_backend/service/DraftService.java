package com.major.devsolver_backend.service;

import com.major.devsolver_backend.dto.DraftRequest;
import com.major.devsolver_backend.dto.DraftResponse;
import com.major.devsolver_backend.dto.PostRequest;
import com.major.devsolver_backend.dto.PostResponse;
import com.major.devsolver_backend.entity.Draft;
import com.major.devsolver_backend.entity.Tag;
import com.major.devsolver_backend.entity.User;
import com.major.devsolver_backend.exception.NotFoundException;
import com.major.devsolver_backend.repository.DraftRepository;
import com.major.devsolver_backend.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DraftService {

    private final DraftRepository draftRepository;
    private final TagRepository tagRepository;
    private final PostService postService;

    // Autosave draft
    public DraftResponse autosave(DraftRequest dto){

        User user = getAuthenticatedUser();
        Draft draft;

        // update existing draft
        if (dto.draftId() != null){
            draft = draftRepository.findById(dto.draftId())
                    .orElseThrow(()-> new NotFoundException("Draft not found!"));

            if (!draft.getUser().getId().equals(user.getId())){
                throw new RuntimeException("Unauthorized!");
            }
        }else{
            // create new draft
            draft = new Draft();
            draft.setUser(user);
        }

        draft.setTitle(dto.title());
        draft.setContent(dto.content());

        Set<Tag> tags = dto.tags()
                .stream()
                .map(this::getOrCreateTag)
                .collect(Collectors.toSet());

        Draft saved = draftRepository.save(draft);
        return mapToDraftResponse(saved);
    }

    // Get my drafts
    public List<DraftResponse> getMyDrafts(){

        User user = getAuthenticatedUser();
        return draftRepository.findByUser(user)
                .stream()
                .map(this::mapToDraftResponse)
                .toList();
    }

    // Publish drafts
    public PostResponse publishDraft(Long draftId){
        User user = getAuthenticatedUser();

        Draft draft = draftRepository.findById(draftId)
                .orElseThrow(()-> new NotFoundException("Draft not found!"));

        if (!draft.getUser().getId().equals(user.getId())){
            throw new RuntimeException("Unauthorized!");
        }

        PostRequest request = new PostRequest(
                draft.getTitle(),
                draft.getContent(),
                draft.getTags()
                        .stream()
                        .map(Tag::getName)
                        .collect(Collectors.toSet())
        );

        PostResponse response = postService.createPost(request);
        draftRepository.delete(draft);

        return response;
    }

    // Delete draft
    public void deleteDraft(Long draftId){

        User user = getAuthenticatedUser();

        Draft draft = draftRepository.findById(draftId)
                .orElseThrow(()-> new NotFoundException("Draft not found!"));

        if (!draft.getUser().getId().equals(user.getId())){
            throw new RuntimeException("Unauthorized!");
        }

        draftRepository.delete(draft);
    }

    // Tag logic
    private Tag getOrCreateTag(String tagName){

        String normalized = tagName.trim().toLowerCase();

        return tagRepository.findByName(normalized)
                .orElseGet(()-> tagRepository.save(
                        Tag.builder()
                                .name(normalized)
                                .build()
                ));
    }

    // DTO Mapper
    private DraftResponse mapToDraftResponse(Draft draft){
        return DraftResponse.builder()
                .id(draft.getId())
                .title(draft.getTitle())
                .content(draft.getContent())
                .tags(
                        draft.getTags()
                                .stream()
                                .map(Tag::getName)
                                .collect(Collectors.toSet())
                )
                .updatedAt(draft.getUpdatedAt())
                .build();
    }

    // private helper methods
    private User getAuthenticatedUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (User) auth.getPrincipal();
    }

}
