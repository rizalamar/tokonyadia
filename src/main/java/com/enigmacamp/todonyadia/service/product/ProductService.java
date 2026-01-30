package com.enigmacamp.todonyadia.service.product;

import com.enigmacamp.todonyadia.entities.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    Product saveProduct(Product product);
    List<Product> getAllProduct();
    Product getProductById(UUID id);
    Product updateProduct(UUID id, Product product);
    void deleteProduct(UUID id);
}
