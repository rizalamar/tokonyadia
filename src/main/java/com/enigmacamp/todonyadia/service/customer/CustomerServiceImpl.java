package com.enigmacamp.todonyadia.service.customer;

import com.enigmacamp.todonyadia.dto.request.CustomerRequest;
import com.enigmacamp.todonyadia.entities.Customer;
import com.enigmacamp.todonyadia.entities.Member;
import com.enigmacamp.todonyadia.repository.CustomerRepository;
import com.enigmacamp.todonyadia.service.member.MemberService;
import com.enigmacamp.todonyadia.utils.exceptions.DataNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.enigmacamp.todonyadia.utils.constants.ResponseMessage.CUSTOMER;
import static com.enigmacamp.todonyadia.utils.constants.ResponseMessage.NOT_FOUND_MESSAGE;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final MemberService memberService;
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, MemberService memberService) {
        this.customerRepository = customerRepository;
        this.memberService = memberService;
    }

    @Override
    public Customer saveCustomer(CustomerRequest payload) {
        Customer customer = Customer.builder()
                .fullname(payload.fullname())
                .address(payload.address())
                .email(payload.email())
                .gender(payload.gender())
                .build();
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(UUID id) {
        if(customerRepository.findById(id).isPresent()){
            return customerRepository.findById(id).get();
        } else {
            throw new DataNotFoundException(NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public Customer findByFullnameAndEmail(String fullname, String email) {
        return customerRepository.findByFullnameAndEmail(fullname, email).get();
    }

    @Override
    public Customer updateCustomer(UUID id,CustomerRequest customerUpdate) {
        Customer customer = Customer.builder()
                .fullname(customerUpdate.fullname())
                .address(customerUpdate.address())
                .email(customerUpdate.email())
                .gender(customerUpdate.gender())
                .build();
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(UUID id) {
        customerRepository.deleteById(id);
    }
}
