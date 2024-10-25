package com.example.OnlineFoodDeliveryService.dto.response;

import com.example.OnlineFoodDeliveryService.enums.OrderStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderResponse {

    String restrauntName;
    String customerName;
    LocalDateTime orderTime;
    float totalAmount;
    String address;
    @Enumerated(EnumType.STRING)
    OrderStatus orderStatus;
    List<OrderedItemResponse> orderedItems;
}
