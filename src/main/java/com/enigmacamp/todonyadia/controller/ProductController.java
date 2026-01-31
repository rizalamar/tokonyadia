package com.enigmacamp.todonyadia.controller;

import com.enigmacamp.todonyadia.dto.request.ProductRequest;
import com.enigmacamp.todonyadia.dto.response.ProductResponse;
import com.enigmacamp.todonyadia.entities.Product;
import com.enigmacamp.todonyadia.service.product.ProductService;
import com.enigmacamp.todonyadia.utils.constants.ApiUrlConstants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(ApiUrlConstants.PRODUCT)
public class ProductController {
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("")
    public ResponseEntity <ProductResponse> addProduct(@RequestBody ProductRequest payload){
        ProductResponse productResponse = productService.saveProduct(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }

    @GetMapping("")
    public Page<ProductResponse> getAllProduct(
        @RequestParam(name = "page", defaultValue = "1") int page,
        @RequestParam(name = "size", defaultValue = "3") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return productService.getAllProduct(pageable);
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable UUID id){
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable UUID id , @RequestBody ProductRequest product){
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable UUID id){
        productService.deleteProduct(id);
    }
}
