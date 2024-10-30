package com.example.OnlineFoodDeliveryService.service;

import com.example.OnlineFoodDeliveryService.enums.DeliveryPersonStatus;
import com.example.OnlineFoodDeliveryService.enums.DeliveryStatus;
import com.example.OnlineFoodDeliveryService.enums.OrderStatus;
import com.example.OnlineFoodDeliveryService.exceptions.ResourceNotFoundException;
import com.example.OnlineFoodDeliveryService.models.Delivery;
import com.example.OnlineFoodDeliveryService.models.DeliveryPerson;
import com.example.OnlineFoodDeliveryService.models.Order;
import com.example.OnlineFoodDeliveryService.repository.DeliveryPersonRepository;
import com.example.OnlineFoodDeliveryService.repository.DeliveryRepository;
import com.example.OnlineFoodDeliveryService.repository.OrderRepository;
import com.example.OnlineFoodDeliveryService.transformer.DeliveryTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private  final DeliveryRepository deliveryRepository;
    private  final OrderRepository orderRepository;
    private  final DeliveryPersonRepository deliveryPersonRepository;


    // create the delivery
    public void createDeliveryForOrderById(UUID id){
        // fetch the order
        Order order=orderRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Order not found."));
        // check order status if it is confirmed or not
        if(order.getOrderStatus()!=(OrderStatus.CONFIRMED)) throw new IllegalStateException("Only confirmed order will be delivered.");
        // check for the delivery person available or not
        DeliveryPerson deliveryPerson=deliveryPersonRepository.findBestRatedAvailableDeliveryPerson().orElseThrow(()->new IllegalStateException("All delivery personnel are currently unavailable. Please try again later."));
        // creat delivery
        Delivery delivery= DeliveryTransformer.createDelivery(order,deliveryPerson);
        // set the delivery refrence in the order
        order.setDelivery(delivery);
        order.setOrderStatus(OrderStatus.OUT_FOR_DELIVERY);
        // make delivery person unavailable
        deliveryPerson.setDeliveryPersonStatus(DeliveryPersonStatus.UNAVAILABLE);

        // save
      //  deliveryRepository.save(delivery);
      orderRepository.save(order);  // parent enitity so enough to save only order
    }

    // update delivery status
    public void updateDeliveryStatus(UUID deliveryId, DeliveryStatus newStatus) {
        // Fetch delivery
        Delivery delivery = deliveryRepository.findById(deliveryId)
                                              .orElseThrow(() -> new ResourceNotFoundException("Delivery not found."));

        DeliveryStatus currentStatus = delivery.getDeliveryStatus();

        // Check if current status is PENDING and new status is EN_ROUTE
        if (currentStatus.equals(DeliveryStatus.PENDING) && newStatus.equals(DeliveryStatus.EN_ROUTE)) {
            // Check if conditions are met to update
            if (delivery.getDeliveryPerson() == null) {
                throw new IllegalStateException("Cannot update to " + newStatus + ". Conditions not met.");
            }

            // Update the status
            delivery.setDeliveryStatus(newStatus);
            deliveryRepository.save(delivery);
        } else if (currentStatus.equals(DeliveryStatus.EN_ROUTE)) {
            throw new IllegalStateException("Delivery status already up to date. Delivery is " + currentStatus + ".");
        } else {
            throw new IllegalStateException(String.format("Invalid status change from %s to %s.", currentStatus, newStatus));
        }
    }


}
