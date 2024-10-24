package com.example.OnlineFoodDeliveryService.controller;


import com.example.OnlineFoodDeliveryService.service.OrderItemService;
import com.example.OnlineFoodDeliveryService.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/order")
public class OrderController {

    private  final OrderService orderService;
    private  final OrderItemService orderItemService;
}
