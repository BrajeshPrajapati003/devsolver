package com.major.devsolver_backend.repository;

import com.major.devsolver_backend.entity.Draft;
import com.major.devsolver_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DraftRepository extends JpaRepository<Draft, Long> {

    List<Draft> findByUser(User user);
}
