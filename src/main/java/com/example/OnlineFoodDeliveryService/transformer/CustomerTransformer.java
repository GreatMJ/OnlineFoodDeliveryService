package com.example.OnlineFoodDeliveryService.transformer;

import com.example.OnlineFoodDeliveryService.dto.request.CustomerRequest;
import com.example.OnlineFoodDeliveryService.dto.response.CustomerResponse;
import com.example.OnlineFoodDeliveryService.models.Customer;

public class CustomerTransformer{

    public static Customer CustomerRequestToCustomer(CustomerRequest customerRequest){
        return Customer.builder()
                .address(customerRequest.getAddress())
                .name(customerRequest.getName())
                .contactNo(customerRequest.getContactNo())
                .gmail(customerRequest.getGmail())
                       .build();
    }

    public static CustomerResponse customerToCustomerResponse(Customer customer){
        return CustomerResponse.builder()
                .address(customer.getAddress())
                .name(customer.getName())
                .gmail(customer.getGmail())
                .contactNo(customer.getContactNo())
                .build();
    }
}
