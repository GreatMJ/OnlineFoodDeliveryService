package com.example.OnlineFoodDeliveryService.controller;

import com.example.OnlineFoodDeliveryService.enums.DeliveryStatus;
import com.example.OnlineFoodDeliveryService.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/delivery")
public class DeliveryController {

    private  final DeliveryService deliveryService;

    @PostMapping("/assign/{id}")
    public ResponseEntity<String> createDeliveryForOrderById(@PathVariable UUID id){
         deliveryService.createDeliveryForOrderById(id);
         return ResponseEntity.ok("Delivery assigned.");
    }

    @PatchMapping("/{id}/status")
    public  ResponseEntity<String> updateDeliveryStatus(@PathVariable UUID id, @RequestParam String newStatus){
        DeliveryStatus status=DeliveryStatus.valueOf(newStatus);
        deliveryService.updateDeliveryStatus(id,status);
        return ResponseEntity.ok(String.format("Status updated to %s",newStatus));
    }


}
