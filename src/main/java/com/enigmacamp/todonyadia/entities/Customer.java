package com.enigmacamp.todonyadia.entities;

import com.enigmacamp.todonyadia.dto.response.CustomerResponse;
import com.enigmacamp.todonyadia.utils.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "m_customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue
    private UUID id;
    private String fullname;
    private String address;
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    public CustomerResponse toResponse(){
        return CustomerResponse.builder()
                .id(getId())
                .fullname(getFullname())
                .address(getAddress())
                .email(getEmail())
                .gender(getGender())
                .member(getMember() != null ? getMember().toResponse() : null)
                .createdAt(getCreatedAt())
                .modifiedAt(getModifiedAt())
                .build();
    }
}
