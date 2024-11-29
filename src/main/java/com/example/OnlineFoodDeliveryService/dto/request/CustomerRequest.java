package com.example.OnlineFoodDeliveryService.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRequest {

    @NotEmpty
    String name;

    String contactNo;

    @NotEmpty
    @Email
    String gmail;

    String address;
}
