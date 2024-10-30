package com.example.OnlineFoodDeliveryService.controller;

import com.example.OnlineFoodDeliveryService.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/delivery")
public class DeliveryController {

    private  final DeliveryService deliveryService;

    @PostMapping("/{id}")
    public ResponseEntity<String> createDeliveryForOrderById(@PathVariable UUID id){
         deliveryService.createDeliveryForOrderById(id);
         return ResponseEntity.ok("Delivery assigned.");
    }
}
