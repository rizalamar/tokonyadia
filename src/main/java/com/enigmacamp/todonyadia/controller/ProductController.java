package com.enigmacamp.todonyadia.controller;

import com.enigmacamp.todonyadia.entities.Product;
import com.enigmacamp.todonyadia.service.product.ProductService;
import com.enigmacamp.todonyadia.utils.constants.ApiUrlConstants;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ApiUrlConstants.PRODUCT)
public class ProductController {
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("")
    public Product addProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }

    @GetMapping("")
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable UUID id){
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable UUID id , @RequestBody Product product){
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable UUID id){
        productService.deleteProduct(id);
    }
}
