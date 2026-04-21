package com.major.devsolver_backend.entity;

import jakarta.persistence.*;

@Entity
public class PostTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Post post;

    @ManyToOne
    private Tag tag;
}
