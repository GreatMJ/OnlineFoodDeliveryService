package com.example.OnlineFoodDeliveryService.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {

    int customerId;
    int restrauntId;

    List<OrderedItemRequest> orderedItems;
}
