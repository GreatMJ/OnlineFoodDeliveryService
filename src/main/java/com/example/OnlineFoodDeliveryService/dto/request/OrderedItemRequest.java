package com.example.OnlineFoodDeliveryService.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderedItemRequest {

    int menuItemId;
    int quantity;
}
