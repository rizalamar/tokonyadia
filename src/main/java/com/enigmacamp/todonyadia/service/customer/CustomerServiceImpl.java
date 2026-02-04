package com.enigmacamp.todonyadia.service.customer;

import com.enigmacamp.todonyadia.dto.request.CustomerRequest;
import com.enigmacamp.todonyadia.dto.request.CustomerSearch;
import com.enigmacamp.todonyadia.dto.response.CustomerResponse;
import com.enigmacamp.todonyadia.entities.Customer;
import com.enigmacamp.todonyadia.entities.Member;
import com.enigmacamp.todonyadia.repository.CustomerRepository;
import com.enigmacamp.todonyadia.repository.MemberRepository;
import com.enigmacamp.todonyadia.service.member.MemberService;
import com.enigmacamp.todonyadia.specification.CustomerSpecification;
import com.enigmacamp.todonyadia.utils.constants.ResponseMessage;
import com.enigmacamp.todonyadia.utils.exceptions.DataNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    MemberService memberService;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponse saveCustomer(CustomerRequest payload) {
        Customer customer = Customer.builder()
                .fullname(payload.getFullname())
                .address(payload.getAddress())
                .email(payload.getEmail())
                .gender(payload.getGender())
                .member(payload.getMember_id())
                .build();

        customerRepository.save(customer);
        return customer.toResponse();
    }

    @Override
    public Customer saveCustomerEntity(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Page<CustomerResponse> getAllCustomer(Pageable pageable, CustomerSearch customerSearch) {
        Specification<Customer> specification = CustomerSpecification.getSpecification(customerSearch);
        return customerRepository.findAll(specification, pageable).map(Customer::toResponse);
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

        customer.setFullname(customerUpdate.getFullname());
        customer.setAddress(customerUpdate.getAddress());
        customer.setEmail(customerUpdate.getEmail());
        customer.setGender(customerUpdate.getGender());

        customerRepository.save(customer);
        return customer.toResponse();
    }

    @Override
    public void deleteCustomer(UUID id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer getCustomerEntityById(UUID id) {
        return customerRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Customer not found"));
    }
}
