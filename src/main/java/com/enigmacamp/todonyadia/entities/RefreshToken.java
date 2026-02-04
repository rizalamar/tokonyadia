package com.enigmacamp.todonyadia.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RefreshToken {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private String token;
    private Instant expired;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
