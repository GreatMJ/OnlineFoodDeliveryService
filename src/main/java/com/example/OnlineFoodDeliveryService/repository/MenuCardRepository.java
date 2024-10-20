package com.example.OnlineFoodDeliveryService.repository;

import com.example.OnlineFoodDeliveryService.models.MenuCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuCardRepository extends JpaRepository<MenuCard,Integer> {
}
