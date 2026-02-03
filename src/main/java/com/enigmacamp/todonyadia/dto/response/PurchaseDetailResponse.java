package com.enigmacamp.todonyadia.dto.response;

import com.enigmacamp.todonyadia.entities.Product;
import com.enigmacamp.todonyadia.entities.Purchase;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.UUID;

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
