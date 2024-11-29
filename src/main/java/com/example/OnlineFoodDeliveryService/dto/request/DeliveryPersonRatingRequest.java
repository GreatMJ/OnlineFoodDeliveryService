package com.example.OnlineFoodDeliveryService.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeliveryPersonRatingRequest {

    @NotNull
    int customerId;
    @NotNull
    UUID deliveryId;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 10, message = "Rating must be no more than 10")
    int rating;
}
