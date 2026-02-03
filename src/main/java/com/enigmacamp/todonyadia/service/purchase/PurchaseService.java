package com.enigmacamp.todonyadia.service.purchase;

import com.enigmacamp.todonyadia.dto.response.PurchaseResponse;
import com.enigmacamp.todonyadia.entities.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PurchaseService {
    Purchase savePurchase(Purchase purchase);
    Page<PurchaseResponse> getAllPurchase(Pageable pageable);
    PurchaseResponse getPurchaseById (UUID id);
//    Purchase updatePurchase(UUID id, Purchase purchase);
//    void deletePurchase(UUID id);
}
