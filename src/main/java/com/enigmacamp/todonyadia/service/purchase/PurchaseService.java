package com.enigmacamp.todonyadia.service.purchase;

import com.enigmacamp.todonyadia.entities.Purchase;

import java.util.List;
import java.util.UUID;

public interface PurchaseService {
    Purchase savePurchase(Purchase purchase);
    List<Purchase> getAllPurchase();
//    Purchase getPurchaseById (UUID id);
//    Purchase updatePurchase(UUID id, Purchase purchase);
//    void deletePurchase(UUID id);
}
