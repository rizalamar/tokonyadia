package com.enigmacamp.todonyadia.service.customer;

import com.enigmacamp.todonyadia.dto.request.CustomerRequest;
import com.enigmacamp.todonyadia.entities.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    Customer saveCustomer(CustomerRequest customer);
    List<Customer> getAllCustomer();
    Customer getCustomerById(UUID id);
    Customer updateCustomer(UUID id, CustomerRequest customer);
    void deleteCustomer(UUID id);
    Customer findByFullnameAndEmail(String fullname, String email);
}
