package com.example.OnlineFoodDeliveryService.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class RestrauntResponse {

    String name;

    String address;
    String gmail;
    String contactNo;
    float rating;
}
