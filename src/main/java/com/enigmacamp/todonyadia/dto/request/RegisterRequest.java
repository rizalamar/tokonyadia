package com.enigmacamp.todonyadia.dto.request;

import com.enigmacamp.todonyadia.utils.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String username;
    private String password;
    private String fullname;
    private String address;
    private String email;
    private Gender gender;
}
