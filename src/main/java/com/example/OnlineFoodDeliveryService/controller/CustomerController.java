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
       try{
           String res=customerService.addCustomer(customerRequest);
           return new ResponseEntity<>(res,
                                       HttpStatus.CREATED);
       }catch (RuntimeException ex){
           return  new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
       }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCustomer(@RequestParam String gmail){
        try{
            String res=customerService.deleteCustomer(gmail);
            return new ResponseEntity<>(res,HttpStatus.OK);
        }catch (ResourceNotFoundException ex){
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
