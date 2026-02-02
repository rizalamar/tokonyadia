package com.enigmacamp.todonyadia.entities;

import com.enigmacamp.todonyadia.dto.response.ProductResponse;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "m_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product extends BaseEntity{

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;
    private String name;
    private Double price;
    private Integer stock;

    public ProductResponse toResponse(){
        return ProductResponse.builder()
                .id(getId())
                .name(getName())
                .price(getPrice())
                .stock(getStock())
                .createdAt(getCreatedAt())
                .modifiedAt(getModifiedAt())
                .build();
    }
}
