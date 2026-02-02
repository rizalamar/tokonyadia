package com.enigmacamp.todonyadia.controller;

import com.enigmacamp.todonyadia.dto.request.CustomerRequest;
import com.enigmacamp.todonyadia.dto.request.CustomerSearch;
import com.enigmacamp.todonyadia.dto.response.CustomerResponse;
import com.enigmacamp.todonyadia.dto.response.PageResponseWrapper;
import com.enigmacamp.todonyadia.service.customer.CustomerService;
import com.enigmacamp.todonyadia.utils.constants.ApiUrlConstants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(ApiUrlConstants.CUSTOMER)
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping()
    public ResponseEntity <CustomerResponse> addCustomer(@RequestBody CustomerRequest payload){
        CustomerResponse customerResponse = customerService.saveCustomer(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerResponse);
    }

    @GetMapping()
    public ResponseEntity<PageResponseWrapper<CustomerResponse>> getAllCustomer(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "3") int size,
            @RequestParam(name = "sort", defaultValue = "id") String sort,
            @RequestParam(name = "order", defaultValue = "asc") String order,
            @ModelAttribute CustomerSearch customerSearch
    ){
        Sort sortOrder = order.equalsIgnoreCase("desc") ? Sort.by(sort).descending() : Sort.by(sort).ascending();
        int firstPage = (page > 0) ? page - 1 : 0;
        Pageable pageable = PageRequest.of(firstPage, size, sortOrder);
        return ResponseEntity.status(HttpStatus.OK).body(new PageResponseWrapper<>(customerService.getAllCustomer(pageable, customerSearch)));
    }

    @GetMapping("/{id}")
    public CustomerResponse getCustomerById(@PathVariable UUID id){
        return customerService.getCustomerById(id);
    }

    @GetMapping("/search")
    public CustomerResponse getCustomerByFullnameAndEmail(
        @RequestParam String fullname,
        @RequestParam String email
    ){
        return customerService.findByFullnameAndEmail(fullname, email);
    }

    @PutMapping("/{id}")
    public CustomerResponse updateCustomer(@PathVariable UUID id,  @RequestBody CustomerRequest customer){
        return customerService.updateCustomer(id, customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable UUID id){
        customerService.deleteCustomer(id);
    }
}
