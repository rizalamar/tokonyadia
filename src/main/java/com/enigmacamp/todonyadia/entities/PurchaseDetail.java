package com.enigmacamp.todonyadia.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "trx_detail")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
}
