package com.example.OnlineFoodDeliveryService.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeliveryPersonRatingRequest {

    int customerId;
    UUID deliveryId;
    int rating;
}
