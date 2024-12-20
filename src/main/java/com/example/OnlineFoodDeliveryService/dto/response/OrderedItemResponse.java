package com.example.OnlineFoodDeliveryService.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderedItemResponse {
    String name;
    int quantity;
    float price;
}
