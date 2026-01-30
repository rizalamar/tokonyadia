package com.enigmacamp.todonyadia.service.customer;

import com.enigmacamp.todonyadia.entities.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    List<Customer> getAllCustomer();
    Customer getCustomerById(UUID id);
    Customer updateCustomer(UUID id, Customer customer);
    void deleteCustomer(UUID id);
    Customer findByFullnameAndEmail(String fullname, String email);
}
