package com.enigmacamp.todonyadia.dto.response;

import com.enigmacamp.todonyadia.entities.PurchaseDetail;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseResponse {
    private UUID id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime transactiondate;

    private CustomerResponse customer;

    private List<PurchaseDetailResponse> purchaseDetails = new ArrayList<>();
    private Double grandTotal;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedAt;
}
