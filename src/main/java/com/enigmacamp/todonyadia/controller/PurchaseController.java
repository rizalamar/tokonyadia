package com.enigmacamp.todonyadia.controller;

import com.enigmacamp.todonyadia.entities.Purchase;
import com.enigmacamp.todonyadia.service.purchase.PurchaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("")
    public Purchase createPurchase(@RequestBody Purchase purchase) {
        return purchaseService.savePurchase(purchase);
    }

    @GetMapping("")
    public List<Purchase> getAllPurchases() {
        return purchaseService.getAllPurchase();
    }
}
