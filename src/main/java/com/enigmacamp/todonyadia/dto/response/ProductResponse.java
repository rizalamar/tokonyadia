package com.enigmacamp.todonyadia.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private UUID id;
    private String name;
    private double price;
    private int stock;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
