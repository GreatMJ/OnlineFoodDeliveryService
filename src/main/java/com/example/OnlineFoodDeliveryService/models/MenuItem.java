package com.example.OnlineFoodDeliveryService.models;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PACKAGE)
@Builder
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String category;
    String name;

    int price;

    @ManyToOne
            @JoinColumn(name = "menucard_id")
    MenuCard menuCard;

}
