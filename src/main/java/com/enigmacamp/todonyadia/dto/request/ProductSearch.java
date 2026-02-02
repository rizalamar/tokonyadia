package com.enigmacamp.todonyadia.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSearch {
    private String name;
    private Double minPrice;
    private Double maxPrice;
    private Integer stock;
}
