package com.enigmacamp.todonyadia.service.product;

import com.enigmacamp.todonyadia.dto.request.ProductRequest;
import com.enigmacamp.todonyadia.dto.request.ProductSearch;
import com.enigmacamp.todonyadia.dto.response.ProductResponse;
import com.enigmacamp.todonyadia.entities.Product;
import com.enigmacamp.todonyadia.repository.ProductRepository;
import com.enigmacamp.todonyadia.specification.ProductSpecification;
import com.enigmacamp.todonyadia.utils.constants.ResponseMessage;
import com.enigmacamp.todonyadia.utils.exceptions.DataNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    public Page<ProductResponse> getAllProduct(Pageable pageable, ProductSearch productSearch) {
        Specification<Product> specification = ProductSpecification.getSpecification(productSearch);
        return productRepository.findAll(specification, pageable).map(Product::toResponse);
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
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new DataNotFoundException(
                String.format(ResponseMessage.NOT_FOUND_MESSAGE, ResponseMessage.PRODUCT, id)
            ));

        product.setName(productUpdate.name());
        product.setPrice(productUpdate.price());
        product.setStock(productUpdate.stock());

        productRepository.save(product);
        return product.toResponse();
    }

    @Override
    public void deleteProduct(UUID id) {
        if(!productRepository.existsById(id)){
            throw new DataNotFoundException(ResponseMessage.NOT_FOUND_MESSAGE);
        }
        productRepository.deleteById(id);
    }

    @Override
    public Product getProductEntityById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Product not found"));
    }
}
