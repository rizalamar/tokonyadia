package com.enigmacamp.todonyadia.dto.response;

public record LoginResponse(
     String accessToken,
     String refreshToken
) {}
