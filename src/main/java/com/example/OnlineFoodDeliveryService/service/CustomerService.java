package com.example.OnlineFoodDeliveryService.service;

import com.example.OnlineFoodDeliveryService.dto.request.CustomerRequest;
import com.example.OnlineFoodDeliveryService.dto.response.CustomerResponse;
import com.example.OnlineFoodDeliveryService.exceptions.ResourceNotFoundException;
import com.example.OnlineFoodDeliveryService.models.Customer;
import com.example.OnlineFoodDeliveryService.repository.CustomerRepository;
import com.example.OnlineFoodDeliveryService.transformer.CustomerTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    // add customer

    public String addCustomer(CustomerRequest customerRequest) {
        // code here
        // check if any user with provided gmail already exist or not
        Customer customer=customerRepository.findByGmail(customerRequest.getGmail());

        if(customer!=null) throw new IllegalArgumentException(String.format("%s is already associated with someone else.",customerRequest.getGmail()));

        customer= CustomerTransformer.CustomerRequestToCustomer(customerRequest);
        customerRepository.save(customer);

        return String.format("%s has successfully registered",customer.getName());


    }

    // delete customer
    public String deleteCustomer(String gmail){
        Customer customer=customerRepository.findByGmail(gmail);

        if(customer==null) throw new ResourceNotFoundException(String.format("%s is incorrect.Verify it and try again.",gmail));

        customerRepository.delete(customer);

        return String.format("%s has removed from system database.",customer.getName());
    }

    // get customer
    public CustomerResponse getCustomerById(int id){
        // fetch customer
        Customer customer =customerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(String.format("Customer with id: not found. Verify id and try again.",id)));

        //conver to response dto
        CustomerResponse customerResponse=CustomerTransformer.customerToCustomerResponse(customer);
        return customerResponse;
    }

}