package com.example.OnlineFoodDeliveryService.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Restraunt {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    int id;

    String name;

    String address;
    @Column(nullable = false,unique = true)
    String gmail;

    @Column(length = 10)
    String contactNo;

    float rating;

    @OneToOne(cascade = CascadeType.ALL)
            @JoinColumn(name = "menucard_id")
    MenuCard menuCard;
}
