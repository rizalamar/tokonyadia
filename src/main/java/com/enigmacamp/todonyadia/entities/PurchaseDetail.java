package com.enigmacamp.todonyadia.entities;

import com.enigmacamp.todonyadia.dto.response.PurchaseDetailResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "trx_detail")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PurchaseDetail {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private Integer quantity;
    private Double priceSell;

    @ManyToOne(fetch = FetchType.EAGER) // agar product detail ikut terbawa
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "trx_purchase_id")
    @JsonIgnoreProperties("purchaseDetails")
    private Purchase purchase;

    public PurchaseDetailResponse toResponse(){
        return PurchaseDetailResponse.builder()
                .id(getId())
                .quantity(getQuantity())
                .priceSell(getPriceSell())
                .subTotal(getQuantity() * getPriceSell()) // Set SubTotal
                .product(getProduct().toResponse())
                .build();
    }
}
