package com.enigmacamp.todonyadia.dto.request;

import com.enigmacamp.todonyadia.entities.Member;
import com.enigmacamp.todonyadia.utils.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSearch {
    private String fullname;
    private String address;
    private String email;
    private Gender gender;
    private Member member;
}
