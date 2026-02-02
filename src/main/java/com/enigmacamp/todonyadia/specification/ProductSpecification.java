package com.enigmacamp.todonyadia.specification;

import com.enigmacamp.todonyadia.dto.request.ProductSearch;
import com.enigmacamp.todonyadia.entities.Product;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {
    public static Specification<Product> getSpecification(ProductSearch productSearch){
        return (root, query,  criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            System.out.println("List: " + predicates);

            if(productSearch.getName() != null && !productSearch.getName().isEmpty()){
                Predicate productNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + productSearch.getName().toLowerCase() + "%");
                predicates.add(productNamePredicate);
            }

            if(productSearch.getMinPrice() != null){
                Predicate minPricePredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("price"), productSearch.getMinPrice());
                predicates.add(minPricePredicate);
            }

            if(productSearch.getMaxPrice() != null){
                Predicate maxPricePredicate = criteriaBuilder.lessThanOrEqualTo(root.get("price"), productSearch.getMaxPrice());
                predicates.add(maxPricePredicate);
            }

            if(productSearch.getStock() != null){
                Predicate stockPredicate = criteriaBuilder.equal(root.get("stock"), productSearch.getStock());
                predicates.add(stockPredicate);
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
