package com.example.OnlineFoodDeliveryService.transformer;

import com.example.OnlineFoodDeliveryService.dto.response.OrderedItemResponse;
import com.example.OnlineFoodDeliveryService.models.MenuItem;
import com.example.OnlineFoodDeliveryService.models.OrderItem;

public class OrderItemTransformer {

    public static OrderedItemResponse orderItemToOrderItemResponse(OrderItem orderItem){
        return OrderedItemResponse.builder()
                .price(orderItem.getPrice())
                .name(orderItem.getName())
                .quantity(orderItem.getQuantity())
                .build();
    }
}
