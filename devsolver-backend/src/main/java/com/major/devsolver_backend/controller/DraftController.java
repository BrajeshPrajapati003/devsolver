package com.major.devsolver_backend.controller;

import com.major.devsolver_backend.dto.DraftRequest;
import com.major.devsolver_backend.dto.DraftResponse;
import com.major.devsolver_backend.dto.PostResponse;
import com.major.devsolver_backend.service.DraftService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drafts")
@RequiredArgsConstructor
public class DraftController {

    private final DraftService draftService;

    // Autosave draft
    @PutMapping("/autosave")
    public ResponseEntity<DraftResponse> autosave(
            @RequestBody DraftRequest dto
            ){
        return ResponseEntity.ok(draftService.autosave(dto));
    }

    // Get my drafts
    @GetMapping
    public ResponseEntity<List<DraftResponse>> getMyDrafts(){
        return ResponseEntity.ok(draftService.getMyDrafts());
    }

    // Publish draft
    @PostMapping("/{draftId}/publish")
    public ResponseEntity<PostResponse> publishDraft(
            @PathVariable Long draftId
    ){
        return ResponseEntity.ok(draftService.publishDraft(draftId));
    }

    // Delete draft
    @DeleteMapping("/{draftId}")
    public ResponseEntity<String> deleteDraft(
            @PathVariable Long draftId
    ){
        draftService.deleteDraft(draftId);
        return ResponseEntity.ok("Draft deleted");
    }
}
