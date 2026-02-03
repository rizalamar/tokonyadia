package com.enigmacamp.todonyadia.service.purchase;

import com.enigmacamp.todonyadia.dto.response.PurchaseResponse;
import com.enigmacamp.todonyadia.entities.Customer;
import com.enigmacamp.todonyadia.entities.Product;
import com.enigmacamp.todonyadia.entities.Purchase;
import com.enigmacamp.todonyadia.entities.PurchaseDetail;
import com.enigmacamp.todonyadia.repository.PurchaseRepository;
import com.enigmacamp.todonyadia.service.customer.CustomerService;
import com.enigmacamp.todonyadia.service.product.ProductService;
import com.enigmacamp.todonyadia.utils.constants.ResponseMessage;
import com.enigmacamp.todonyadia.utils.exceptions.DataNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

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

        preparedPurchaseDetail(purchase);

        return purchaseRepository.save(purchase);
    }

    private void preparedPurchaseDetail(Purchase purchase) {
        for (PurchaseDetail detail : purchase.getPurchaseDetails()) {
            Product product = productService.getProductEntityById(detail.getProduct().getId());

            // Validasi jika stok produk kurang dari quantity
            if (product.getStock() < detail.getQuantity()) {
                throw new RuntimeException("Out of stock: " + product.getName());
            }

            // Stok produk berkurang sesuai dengan quantity pembelian produk
            product.setStock(product.getStock() - detail.getQuantity());

            // PriceSell set dari harga produk waktu pembelian
            detail.setPriceSell(product.getPrice());

            detail.setProduct(product);
            detail.setPurchase(purchase);
        }
    }

    public Page<PurchaseResponse> getAllPurchase(Pageable pageable){
        return purchaseRepository.findAll(pageable).map(Purchase::toResponse);
    }

    @Override
    public PurchaseResponse getPurchaseById(UUID id) {
        Purchase purchase = purchaseRepository.findById(id)
            .orElseThrow(
                () -> new DataNotFoundException(String.format(ResponseMessage.NOT_FOUND_MESSAGE, ResponseMessage.PRODUCT, id)
            ));
        return purchase.toResponse();
    }
}
