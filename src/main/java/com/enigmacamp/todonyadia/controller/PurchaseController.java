package com.enigmacamp.todonyadia.controller;

import com.enigmacamp.todonyadia.dto.response.PageResponseWrapper;
import com.enigmacamp.todonyadia.dto.response.PurchaseResponse;
import com.enigmacamp.todonyadia.entities.Purchase;
import com.enigmacamp.todonyadia.service.purchase.PurchaseService;
import org.apache.coyote.Response;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<PageResponseWrapper<PurchaseResponse>> getAllPurchase(
        @RequestParam(name = "page", defaultValue = "1") int page,
        @RequestParam(name = "size", defaultValue = "3") int size,
        @RequestParam(name = "sort", defaultValue = "id") String sort,
        @RequestParam(name = "order", defaultValue = "asc") String order
    ) {
        Sort sortOrder = order.equalsIgnoreCase("desc") ? Sort.by(sort).descending() : Sort.by(sort).ascending();
        int firstPage = (page > 0) ? page - 1 : 0;
        Pageable pageable = PageRequest.of(firstPage, size, sortOrder);
        return ResponseEntity.status(HttpStatus.OK).body(new PageResponseWrapper<>(purchaseService.getAllPurchase(pageable))) ;
    }

    @GetMapping("/{id}")
    public PurchaseResponse getPurchaseById(@PathVariable UUID id){
        return purchaseService.getPurchaseById(id);
    }
}
