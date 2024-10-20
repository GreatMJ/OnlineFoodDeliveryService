package com.example.OnlineFoodDeliveryService.dto.request;


import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RestrauntRequest {

    String name;

    String gmail;

    String contactNo;

    String address;

    int rating;
}
