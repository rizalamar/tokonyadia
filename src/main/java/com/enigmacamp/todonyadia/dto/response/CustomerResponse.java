package com.enigmacamp.todonyadia.dto.response;

import com.enigmacamp.todonyadia.entities.Member;
import com.enigmacamp.todonyadia.utils.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {
    private UUID id;
    private String fullname;
    private String address;
    private String email;
    private Gender gender;
    private MemberResponse member;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedAt;
}
