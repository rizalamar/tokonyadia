package com.enigmacamp.todonyadia.repository;

import com.enigmacamp.todonyadia.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    // Query method
    // Spring otomatis bikin query: SELECT fullname, email FROM CUSTOMER WHERE fullname AND email
    // untuk menghindari null: pakai optional (data atau tidak ada?)
    // atau list<suctomer>
    Optional<Customer> findByFullnameAndEmail(String fullname, String email);
}
