package com.major.devsolver_backend.repository;

import com.major.devsolver_backend.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByUserId(Long userId, Pageable pageable);
    Page<Post> findByTags_Name(String name, Pageable pageable);
}
