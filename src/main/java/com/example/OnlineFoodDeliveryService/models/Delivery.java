package com.example.OnlineFoodDeliveryService.models;

import com.example.OnlineFoodDeliveryService.enums.DeliveryStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @OneToOne(mappedBy = "delivery")
    Order order;

    @ManyToOne(cascade = CascadeType.PERSIST)
            @JoinColumn(name = "delivery_person_id")
    DeliveryPerson deliveryPerson;

    String deliverAddress;

    @Enumerated(EnumType.STRING)
  DeliveryStatus deliveryStatus;

    LocalDateTime assingedTime;
    LocalDateTime deliveryTime;


}
