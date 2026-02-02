package com.enigmacamp.todonyadia.service.purchase_detail;

import com.enigmacamp.todonyadia.entities.PurchaseDetail;
import com.enigmacamp.todonyadia.repository.PurchaseDetailRepository;
import org.springframework.stereotype.Service;

@Service
public class PurchaseDetailServiceImpl implements PurchaseDetailService{
    PurchaseDetailRepository purchaseDetailRepository;

    public PurchaseDetailServiceImpl(PurchaseDetailRepository purchaseDetailRepository) {
        this.purchaseDetailRepository = purchaseDetailRepository;
    }

    public PurchaseDetail savePurchaseDetail(PurchaseDetail purchaseDetail){
        return purchaseDetailRepository.save(purchaseDetail);
    }
}
