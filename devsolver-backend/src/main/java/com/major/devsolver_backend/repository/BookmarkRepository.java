package com.major.devsolver_backend.repository;

import com.major.devsolver_backend.entity.Bookmark;
import com.major.devsolver_backend.entity.Post;
import com.major.devsolver_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    Optional<Bookmark> findByUserAndPost(User user, Post post);

    List<Bookmark> findByUser(User user);
}
