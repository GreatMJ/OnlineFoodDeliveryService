package com.example.OnlineFoodDeliveryService.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeliveryPersonRequest {

    String name;
    String contactNo;
    String gmail;

}
