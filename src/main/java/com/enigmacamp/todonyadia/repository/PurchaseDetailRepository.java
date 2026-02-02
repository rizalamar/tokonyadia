package com.enigmacamp.todonyadia.repository;

import com.enigmacamp.todonyadia.entities.PurchaseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetail, UUID> {
}
