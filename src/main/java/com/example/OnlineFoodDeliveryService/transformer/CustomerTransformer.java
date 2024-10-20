package com.example.OnlineFoodDeliveryService.transformer;

import com.example.OnlineFoodDeliveryService.dto.request.CustomerRequest;
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
}
