package com.enigmacamp.todonyadia.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import jdk.jshell.Snippet;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseDetailResponse {
    private UUID id;
    private Integer quantity;
    private Double priceSell;
    private Double subTotal;
    private ProductResponse product;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedAt;
}
