package com.example.OnlineFoodDeliveryService.dto.request;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.antlr.v4.runtime.misc.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRequest {

    String name;
    String contactNo;

    String gmail;
    String address;
}
