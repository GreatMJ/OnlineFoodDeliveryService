package com.example.OnlineFoodDeliveryService.service;

import com.example.OnlineFoodDeliveryService.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {


    private final OrderRepository orderRepository;



}
