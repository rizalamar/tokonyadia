package com.enigmacamp.todonyadia.service.product;

import com.enigmacamp.todonyadia.dto.request.ProductRequest;
import com.enigmacamp.todonyadia.dto.response.ProductResponse;
import com.enigmacamp.todonyadia.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductResponse saveProduct(ProductRequest product);
    Page<ProductResponse> getAllProduct(Pageable pageable);
    ProductResponse getProductById(UUID id);
    ProductResponse updateProduct(UUID id, ProductRequest product);
    void deleteProduct(UUID id);
}
