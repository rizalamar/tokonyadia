package com.enigmacamp.todonyadia.service.customer;

import com.enigmacamp.todonyadia.dto.request.CustomerRequest;
import com.enigmacamp.todonyadia.dto.request.CustomerSearch;
import com.enigmacamp.todonyadia.dto.response.CustomerResponse;
import com.enigmacamp.todonyadia.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    CustomerResponse saveCustomer(CustomerRequest customer);
    Customer saveCustomerEntity(Customer customer);
    Page<CustomerResponse> getAllCustomer(Pageable pageable, CustomerSearch customerSearch);
    CustomerResponse getCustomerById(UUID id);
    Customer getCustomerEntityById(UUID id);
    CustomerResponse updateCustomer(UUID id, CustomerRequest customer);
    void deleteCustomer(UUID id);
    CustomerResponse findByFullnameAndEmail(String fullname, String email);
}
