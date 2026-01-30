package com.enigmacamp.todonyadia.controller;

import com.enigmacamp.todonyadia.entities.Customer;
import com.enigmacamp.todonyadia.service.customer.CustomerService;
import com.enigmacamp.todonyadia.utils.constants.ApiUrlConstants;
import jakarta.servlet.ServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ApiUrlConstants.CUSTOMER)
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping()
    public Customer addCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    @GetMapping()
    public List<Customer> getAllCustomer(){
        return customerService.getAllCustomer();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable UUID id){
        return customerService.getCustomerById(id);
    }

    @GetMapping("/search")
    public Customer getCustomerByFullnameAndEmail(
        @RequestParam String fullname,
        @RequestParam String email
    ){
        return customerService.findByFullnameAndEmail(fullname, email);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable UUID id,  @RequestBody Customer customer){
        return customerService.updateCustomer(id, customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable UUID id){
        customerService.deleteCustomer(id);
    }
}
