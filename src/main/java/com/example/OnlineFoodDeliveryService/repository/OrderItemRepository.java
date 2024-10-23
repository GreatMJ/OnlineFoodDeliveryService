package com.example.OnlineFoodDeliveryService.repository;

import com.example.OnlineFoodDeliveryService.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
}
