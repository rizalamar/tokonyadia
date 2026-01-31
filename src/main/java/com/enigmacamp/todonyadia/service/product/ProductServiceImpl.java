package com.enigmacamp.todonyadia.service.product;

import com.enigmacamp.todonyadia.dto.request.ProductRequest;
import com.enigmacamp.todonyadia.dto.response.ProductResponse;
import com.enigmacamp.todonyadia.entities.Product;
import com.enigmacamp.todonyadia.repository.ProductRepository;
import com.enigmacamp.todonyadia.utils.constants.ResponseMessage;
import com.enigmacamp.todonyadia.utils.exceptions.DataNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse saveProduct(ProductRequest payload) {
        Product product = Product.builder()
                .name(payload.name())
                .price(payload.price())
                .stock(payload.stock())
                .build();
        productRepository.save(product);
        return product.toResponse();
    }

    @Override
    public Page<ProductResponse> getAllProduct(Pageable pageable) {
        return productRepository.findAll(pageable).map(Product::toResponse);
    }

    @Override
    public ProductResponse getProductById(UUID id) {
       Product product = productRepository.findById(id)
               .orElseThrow(
                   () -> new DataNotFoundException(String.format(ResponseMessage.NOT_FOUND_MESSAGE, ResponseMessage.PRODUCT, id)
               ));
       return product.toResponse();
    }

    @Override
    public ProductResponse updateProduct(UUID id, ProductRequest productUpdate) {
        Product product = Product.builder()
                .id(id)
                .name(productUpdate.name())
                .price(productUpdate.price())
                .stock(productUpdate.stock())
                .build();
         productRepository.save(product);
         return product.toResponse();
    }

    @Override
    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }
}
