package com.example.OnlineFoodDeliveryService.repository;

import com.example.OnlineFoodDeliveryService.models.DeliveryPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryPersonRepository extends JpaRepository<DeliveryPerson,Integer> {
}
