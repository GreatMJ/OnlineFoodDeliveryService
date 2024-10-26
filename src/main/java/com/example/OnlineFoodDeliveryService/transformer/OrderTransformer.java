package com.example.OnlineFoodDeliveryService.transformer;

import com.example.OnlineFoodDeliveryService.dto.response.OrderResponse;
import com.example.OnlineFoodDeliveryService.dto.response.OrderedItemResponse;
import com.example.OnlineFoodDeliveryService.models.Order;
import com.example.OnlineFoodDeliveryService.models.OrderItem;

import java.util.List;

public class OrderTransformer {

    public  static OrderResponse orderToOrderResponse(Order order, List<OrderedItemResponse> orderedItemResponseList){
        return OrderResponse.builder()
                .orderedItems(orderedItemResponseList)
                .orderStatus(order.getOrderStatus())
                .orderDate(order.getOrderTime().toLocalDate())
                .orderTime(order.getOrderTime().toLocalTime().withNano(0))
                .restrauntName(order.getRestraunt().getName())
                .customerName(order.getCustomer().getName())
                .address(order.getAddress())
                .totalAmount(order.getAmount())
                            .build();
    }
}
