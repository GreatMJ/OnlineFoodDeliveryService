package com.example.OnlineFoodDeliveryService.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class MenuCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @OneToOne(mappedBy = "menuCard")
    Restraunt restraunt;

    @OneToMany(mappedBy = "menuCard",cascade = CascadeType.ALL)
    List<MenuItem> menuItemList;
}
