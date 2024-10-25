package com.example.OnlineFoodDeliveryService.controller;

import com.example.OnlineFoodDeliveryService.dto.request.CustomerRequest;
import com.example.OnlineFoodDeliveryService.exceptions.ResourceNotFoundException;
import com.example.OnlineFoodDeliveryService.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private  final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> addCustomer(@RequestBody CustomerRequest customerRequest){

           String res=customerService.addCustomer(customerRequest);
           return new ResponseEntity<>(res,
                                       HttpStatus.CREATED);

    }

    @DeleteMapping
    public ResponseEntity<String> deleteCustomer(@RequestParam String gmail){

            String res=customerService.deleteCustomer(gmail);
            return ResponseEntity.ok(res);

    }
}
