package com.major.devsolver_backend.entity;

import com.major.devsolver_backend.entity.enums.VoteType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Entity
@Builder @Data
@NoArgsConstructor @AllArgsConstructor
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {
                "user_id", "post_id"
        })
) // prevent multiple votes by same user
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private VoteType type;

    @ManyToOne
    private User user;

    @ManyToOne
    private Post post;
}
