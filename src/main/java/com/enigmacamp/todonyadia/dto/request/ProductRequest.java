package com.enigmacamp.todonyadia.dto.request;

public record ProductRequest(
        String name,
        double price,
        int stock
) {
}
