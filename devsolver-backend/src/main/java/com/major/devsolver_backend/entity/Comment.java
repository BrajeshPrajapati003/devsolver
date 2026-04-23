package com.major.devsolver_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

//    @Column(nullable = true) // TEMP
//    private Instant createdAt;

//    @Column(nullable = false, updatable = false)
//    private Instant createdAt;
//
//    @PrePersist
//    void onCreate(){
//        this.createdAt = Instant.now();
//    }

    @CreationTimestamp
    private Instant createdAt;
}
