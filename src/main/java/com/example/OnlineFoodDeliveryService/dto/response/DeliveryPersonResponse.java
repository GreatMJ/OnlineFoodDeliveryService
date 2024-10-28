package com.example.OnlineFoodDeliveryService.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DeliveryPersonResponse {

    String name;
    String contactNo;
    String gmail;
    float rating;
    int deliveriesCompleted;
}
