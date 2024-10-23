package com.example.OnlineFoodDeliveryService.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    @Column(unique = true,length = 10)
    String contactNo;

    @Column (unique = true,nullable = false)
    String gmail;

    String address;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.PERSIST)
    List<Order> orderList;
}
