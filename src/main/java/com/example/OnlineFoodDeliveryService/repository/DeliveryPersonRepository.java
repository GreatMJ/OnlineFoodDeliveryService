package com.example.OnlineFoodDeliveryService.repository;

import com.example.OnlineFoodDeliveryService.models.DeliveryPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryPersonRepository extends JpaRepository<DeliveryPerson,Integer> {

    @Query(value = "select * from delivery_person where delivery_person_status= 'AVAILABLE' order by average_rating limit 1",nativeQuery = true)
 Optional< DeliveryPerson > findBestRatedAvailableDeliveryPerson();
}
