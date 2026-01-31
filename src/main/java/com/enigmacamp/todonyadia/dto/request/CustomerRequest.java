package com.enigmacamp.todonyadia.dto.request;

import com.enigmacamp.todonyadia.utils.Gender;

public record CustomerRequest(
        String fullname,
        String address,
        String email,
        Gender gender
) {
}
