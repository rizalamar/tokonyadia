package com.enigmacamp.todonyadia.controller;

import com.enigmacamp.todonyadia.dto.request.ProductRequest;
import com.enigmacamp.todonyadia.dto.request.ProductSearch;
import com.enigmacamp.todonyadia.dto.response.PageResponseWrapper;
import com.enigmacamp.todonyadia.dto.response.ProductResponse;
import com.enigmacamp.todonyadia.service.product.ProductService;
import com.enigmacamp.todonyadia.utils.constants.ApiUrlConstants;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity <ProductResponse> addProduct(@RequestBody ProductRequest payload){
        ProductResponse productResponse = productService.saveProduct(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }

    @GetMapping("")
    public ResponseEntity<PageResponseWrapper<ProductResponse>> getAllProduct(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "3") int size,
            @RequestParam(name = "sort", defaultValue = "id") String sort,
            @RequestParam(name = "order", defaultValue = "asc") String order,
            @ModelAttribute ProductSearch productSearch
            ){
        Sort sortOrder = order.equalsIgnoreCase("desc") ? Sort.by(sort).descending() : Sort.by(sort).ascending();
        int firstPage = (page > 0) ? page - 1 : 0;
        Pageable pageable = PageRequest.of(firstPage, size, sortOrder);
        return ResponseEntity.status(HttpStatus.OK).body(new PageResponseWrapper<>(productService.getAllProduct(pageable, productSearch)));
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
