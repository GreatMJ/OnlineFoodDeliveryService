package com.example.OnlineFoodDeliveryService.transformer;

import com.example.OnlineFoodDeliveryService.enums.DeliveryStatus;
import com.example.OnlineFoodDeliveryService.models.Delivery;
import com.example.OnlineFoodDeliveryService.models.DeliveryPerson;
import com.example.OnlineFoodDeliveryService.models.Order;

import java.time.LocalDateTime;

public class DeliveryTransformer {

    public static Delivery createDelivery(Order order, DeliveryPerson deliveryPerson){
        return  Delivery.builder()
                .order(order)
                .deliveryPerson(deliveryPerson)
                .deliveryStatus(DeliveryStatus.PENDING)
                .assingedTime(LocalDateTime.now())
                .deliverAddress(order.getAddress())
                        .build();
    }
}
