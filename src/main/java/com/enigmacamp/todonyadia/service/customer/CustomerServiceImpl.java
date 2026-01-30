package com.enigmacamp.todonyadia.service.customer;

import com.enigmacamp.todonyadia.entities.Customer;
import com.enigmacamp.todonyadia.entities.Member;
import com.enigmacamp.todonyadia.repository.CustomerRepository;
import com.enigmacamp.todonyadia.service.member.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final MemberService memberService;
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, MemberService memberService) {
        this.customerRepository = customerRepository;
        this.memberService = memberService;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(UUID id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public Customer findByFullnameAndEmail(String fullname, String email) {
        return customerRepository.findByFullnameAndEmail(fullname, email).get();
    }

    @Override
    public Customer updateCustomer(UUID id,Customer customerUpdate) {
        Customer customer = getCustomerById(id);

        customer.setFullname(customerUpdate.getFullname());
        customer.setAddress(customerUpdate.getAddress());
        customer.setEmail(customerUpdate.getEmail());
        customer.setGender(customerUpdate.getGender());

        return saveCustomer(customer);
    }

    @Override
    public void deleteCustomer(UUID id) {
        customerRepository.deleteById(id);
    }
}
