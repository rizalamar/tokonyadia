package com.enigmacamp.todonyadia.entities;

import com.enigmacamp.todonyadia.dto.response.MemberResponse;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "m_member_role",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roleSet = new HashSet<>();

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
