package com.example.OnlineFoodDeliveryService.models;


import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id;

    String name;

    int quantity;

    int price;

    @ManyToOne
            @JoinColumn(name = "order_id",nullable = false)
    Order order;
}
