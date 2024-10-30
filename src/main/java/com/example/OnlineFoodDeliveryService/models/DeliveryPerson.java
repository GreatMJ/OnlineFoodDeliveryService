package com.example.OnlineFoodDeliveryService.models;

import com.example.OnlineFoodDeliveryService.enums.DeliveryPersonStatus;
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
public class DeliveryPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int id;

    String name;

    @Column(unique = true)
    String contactNo;
    String gmail;

    @Enumerated(EnumType.STRING)
    DeliveryPersonStatus deliveryPersonStatus;



    @OneToMany(mappedBy = "deliveryPerson")
    List<Delivery> deliveryList;

    int deliveriesCompleted;

    long ratingSum;
    long ratingCount;
    float averageRating;
}
