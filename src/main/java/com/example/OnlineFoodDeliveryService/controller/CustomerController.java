package com.example.OnlineFoodDeliveryService.controller;

import com.example.OnlineFoodDeliveryService.dto.request.CustomerRequest;
import com.example.OnlineFoodDeliveryService.dto.response.CustomerResponse;
import com.example.OnlineFoodDeliveryService.dto.response.OrderResponse;
import com.example.OnlineFoodDeliveryService.exceptions.ResourceNotFoundException;
import com.example.OnlineFoodDeliveryService.service.CustomerService;
import com.example.OnlineFoodDeliveryService.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@Validated
@RequiredArgsConstructor
public class CustomerController {

    private  final CustomerService customerService;
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> addCustomer(@Valid @RequestBody CustomerRequest customerRequest){

           String res=customerService.addCustomer(customerRequest);
           return new ResponseEntity<>(res,
                                       HttpStatus.CREATED);

    }

    @DeleteMapping
    public ResponseEntity<String> deleteCustomer( @RequestParam @NotBlank @Email String gmail){

            String res=customerService.deleteCustomer(gmail);
            return ResponseEntity.ok(res);

    }

    @GetMapping("/{id}/orders")
    public  ResponseEntity<?> getCustomerOrders(@PathVariable  int id){
        List<OrderResponse> orderResponseList=orderService.getCustomerOrders(id);

        if(orderResponseList.isEmpty()) return ResponseEntity.ok("Customer hasn't placed any order yet.");

        return ResponseEntity.ok(orderResponseList);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<CustomerResponse> getCustomerById(@PathVariable int id){
        CustomerResponse customerResponse=customerService.getCustomerById(id);
        return ResponseEntity.ok(customerResponse);
    }


}
