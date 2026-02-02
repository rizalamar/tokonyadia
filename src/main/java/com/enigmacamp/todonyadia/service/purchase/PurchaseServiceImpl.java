package com.enigmacamp.todonyadia.service.purchase;

import com.enigmacamp.todonyadia.entities.Customer;
import com.enigmacamp.todonyadia.entities.Product;
import com.enigmacamp.todonyadia.entities.Purchase;
import com.enigmacamp.todonyadia.entities.PurchaseDetail;
import com.enigmacamp.todonyadia.repository.PurchaseRepository;
import com.enigmacamp.todonyadia.service.customer.CustomerService;
import com.enigmacamp.todonyadia.service.product.ProductService;
import com.enigmacamp.todonyadia.service.purchase_detail.PurchaseDetailService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class PurchaseServiceImpl implements PurchaseService {
    private final ProductService productService;
    private final PurchaseRepository purchaseRepository;
    private final CustomerService customerService;


    public PurchaseServiceImpl(ProductService productService, PurchaseRepository purchaseRepository, CustomerService customerService) {
        this.productService = productService;
        this.purchaseRepository = purchaseRepository;
        this.customerService = customerService;
    }

    public Purchase savePurchase(Purchase purchase){
        // date otomatis
        if (purchase.getTransactiondate() == null) purchase.setTransactiondate(LocalDateTime.now());

        Customer customer = customerService.getCustomerEntityById(purchase.getCustomer().getId());
        purchase.setCustomer(customer);

        for (PurchaseDetail detail : purchase.getPurchaseDetails()) {
            Product product = productService.getProductEntityById(detail.getProduct().getId());

            if (product.getStock() < detail.getQuantity()) {
                throw new RuntimeException("Out of stock: " + product.getName());
            }

            product.setStock(product.getStock() - detail.getQuantity());

            detail.setPriceSell(product.getPrice());

            detail.setProduct(product);
            detail.setPurchase(purchase);
        }

        return purchaseRepository.saveAndFlush(purchase);
    }

    public List<Purchase> getAllPurchase(){
        return purchaseRepository.findAll();
    }
}
