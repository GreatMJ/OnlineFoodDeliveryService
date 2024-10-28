package com.example.OnlineFoodDeliveryService.transformer;

import com.example.OnlineFoodDeliveryService.dto.request.DeliveryPersonRequest;
import com.example.OnlineFoodDeliveryService.dto.response.DeliveryPersonResponse;
import com.example.OnlineFoodDeliveryService.enums.DeliveryPersonStatus;
import com.example.OnlineFoodDeliveryService.models.DeliveryPerson;

public class DeliveryPersonTransformer {

    public static DeliveryPerson requestToEntity(DeliveryPersonRequest deliveryPersonRequest){
        return DeliveryPerson.builder()
                .name(deliveryPersonRequest.getName())
                .contactNo(deliveryPersonRequest.getContactNo())
                .gmail(deliveryPersonRequest.getGmail())
                .deliveryPersonStatus(DeliveryPersonStatus.AVAILABLE)
                             .build();
    }

    public static DeliveryPersonResponse entityToResponse(DeliveryPerson deliveryPerson){
        return DeliveryPersonResponse.builder()
                .contactNo(deliveryPerson.getContactNo())
                .deliveriesCompleted(deliveryPerson.getDeliveriesCompleted())
                .gmail(deliveryPerson.getGmail())
                .name(deliveryPerson.getName())
                .rating(deliveryPerson.getRating())
                                     .build();
    }
}
