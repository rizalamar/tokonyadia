package com.enigmacamp.todonyadia.service.customer;

import com.enigmacamp.todonyadia.dto.request.CustomerRequest;
import com.enigmacamp.todonyadia.dto.response.CustomerResponse;
import com.enigmacamp.todonyadia.entities.Customer;
import com.enigmacamp.todonyadia.repository.CustomerRepository;
import com.enigmacamp.todonyadia.service.member.MemberService;
import com.enigmacamp.todonyadia.utils.constants.ResponseMessage;
import com.enigmacamp.todonyadia.utils.exceptions.DataNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final MemberService memberService;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, MemberService memberService) {
        this.customerRepository = customerRepository;
        this.memberService = memberService;
    }

    @Override
    public CustomerResponse saveCustomer(CustomerRequest payload) {
        Customer customer = Customer.builder()
                .fullname(payload.fullname())
                .address(payload.address())
                .email(payload.email())
                .gender(payload.gender())
                .build();
        customerRepository.save(customer);
        return customer.toResponse();
    }

    @Override
    public Page<CustomerResponse> getAllCustomer(Pageable pageable) {
        return customerRepository.findAll(pageable).map(Customer::toResponse);
    }

    @Override
    public CustomerResponse getCustomerById(UUID id) {
        Customer customer = customerRepository.findById(id)
            .orElseThrow(
                () -> new DataNotFoundException(String.format(ResponseMessage.NOT_FOUND_MESSAGE, ResponseMessage.PRODUCT, id))
            );
        return customer.toResponse();
    }

    @Override
    public CustomerResponse findByFullnameAndEmail(String fullname, String email) {
        Customer customer = customerRepository.findByFullnameAndEmail(fullname, email)
            .orElseThrow(
                () -> new DataNotFoundException(String.format(ResponseMessage.NOT_FOUND_MESSAGE, ResponseMessage.PRODUCT, fullname))
            );
        return customer.toResponse() ;
    }

    @Override
    public CustomerResponse updateCustomer(UUID id,CustomerRequest customerUpdate) {
        Customer customer = customerRepository.findById(id)
            .orElseThrow(
                () -> new DataNotFoundException(String.format(ResponseMessage.NOT_FOUND_MESSAGE, ResponseMessage.PRODUCT, id))
            );

        customer.setFullname(customerUpdate.fullname());
        customer.setAddress(customerUpdate.address());
        customer.setEmail(customerUpdate.email());
        customer.setGender(customerUpdate.gender());

        customerRepository.save(customer);
        return customer.toResponse();
    }

    @Override
    public void deleteCustomer(UUID id) {
        customerRepository.deleteById(id);
    }
}
