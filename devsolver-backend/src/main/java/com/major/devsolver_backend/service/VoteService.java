package com.major.devsolver_backend.service;

import com.major.devsolver_backend.entity.Post;
import com.major.devsolver_backend.entity.User;
import com.major.devsolver_backend.entity.Vote;
import com.major.devsolver_backend.entity.enums.VoteType;
import com.major.devsolver_backend.exception.NotFoundException;
import com.major.devsolver_backend.repository.PostRepository;
import com.major.devsolver_backend.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteService {

    // add, delete, toggle vote
    // get vote count

    private final VoteRepository voteRepository;
    private final PostRepository postRepository;

    public String vote(Long postId, VoteType type){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new NotFoundException("Post not found!"));

        var existingVote = voteRepository.findByUserIdAndPostId(user.getId(), postId);

        // Toggle logic
        if (existingVote.isPresent()){
            Vote vote = existingVote.get();

            if (vote.getType() == type){
                voteRepository.delete(vote);
                return "Vote removed!";
            }else{
                vote.setType(type);
                voteRepository.save(vote);
                return "Vote updated!";
            }
        }

        Vote newVote = Vote.builder()
                .type(type)
                .user(user)
                .post(post)
                .build();

        voteRepository.save(newVote);
        return "Vote added!";
    }

}
