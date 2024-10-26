package com.example.OnlineFoodDeliveryService.models;

import com.example.OnlineFoodDeliveryService.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Table(name = "customer_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

  UUID id;

    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    Customer customer;

    @ManyToOne
    @JoinColumn(name = "restraunt_id",nullable = false)
    Restraunt restraunt;

    LocalDateTime orderTime;

    Float amount;

    String address;


    @Enumerated(EnumType.STRING)
   OrderStatus orderStatus;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    List<OrderItem> orderItemList;
}
