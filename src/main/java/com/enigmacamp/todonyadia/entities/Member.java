package com.enigmacamp.todonyadia.entities;

import com.enigmacamp.todonyadia.dto.response.MemberResponse;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "m_member")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member extends BaseEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private String username;
    private String password;

    public MemberResponse toResponse(){
        return MemberResponse.builder()
                .id(getId())
                .username(getUsername())
                .password(getPassword())
                .createdAt(getCreatedAt())
                .modifiedAt(getModifiedAt())
                .build();
    }
}
