package com.enigmacamp.todonyadia.service.product;

import com.enigmacamp.todonyadia.entities.Product;
import com.enigmacamp.todonyadia.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(UUID id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product updateProduct(UUID id, Product productUpdate) {
        Product product = getProductById(id);

        product.setName(productUpdate.getName());
        product.setPrice(productUpdate.getPrice());
        product.setStock(productUpdate.getStock());

        return saveProduct(product);
    }

    @Override
    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }
}
