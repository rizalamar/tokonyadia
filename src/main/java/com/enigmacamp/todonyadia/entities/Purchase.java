package com.enigmacamp.todonyadia.entities;

import com.enigmacamp.todonyadia.dto.response.PurchaseResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "trx_purchase")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Purchase extends BaseEntity {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime transactiondate;

    @ManyToOne(fetch = FetchType.EAGER) // agar info customer terbawa
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /**
     * cascade = CascadeType.ALL
     * Saat save Purchase, semua isi purchaseDetails otomatis ikut tersimpan ke database
     */
    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("purchase")
    private List<PurchaseDetail> purchaseDetails = new ArrayList<>();

    public PurchaseResponse toResponse(){
        return PurchaseResponse.builder()
                .id(getId())
                .transactiondate(getTransactiondate())
                .customer(getCustomer().toResponse())
                .purchaseDetails(getPurchaseDetails())
                .createdAt(getCreatedAt())
                .modifiedAt(getModifiedAt())
                .build();
    }
}
