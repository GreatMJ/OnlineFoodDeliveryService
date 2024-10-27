package com.example.OnlineFoodDeliveryService.controller;


import com.example.OnlineFoodDeliveryService.dto.request.OrderRequest;
import com.example.OnlineFoodDeliveryService.dto.response.OrderResponse;
import com.example.OnlineFoodDeliveryService.service.OrderItemService;
import com.example.OnlineFoodDeliveryService.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/order")
public class OrderController {

    private  final OrderService orderService;
    private  final OrderItemService orderItemService;

    @PostMapping
    public ResponseEntity<String > placeOrder(@RequestBody OrderRequest orderRequest){
        String res=orderService.placeoOrder(orderRequest);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<OrderResponse> getOrderById(@PathVariable UUID id){
        OrderResponse orderResponse=orderService.getOrderById(id);
        return ResponseEntity.ok(orderResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderById(@PathVariable UUID id){
        String res= orderService.deleteOrderById(id);
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<String> cancelOrderById(@PathVariable UUID id){
        orderService.cancelOrderById(id);
        return ResponseEntity.ok("Order got cancelled.");
    }



}
