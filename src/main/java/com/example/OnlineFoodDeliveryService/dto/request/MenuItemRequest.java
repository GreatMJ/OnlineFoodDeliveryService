package com.example.OnlineFoodDeliveryService.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MenuItemRequest {

    String category;
    String name;
    int price;

    int menuCardId;
}
