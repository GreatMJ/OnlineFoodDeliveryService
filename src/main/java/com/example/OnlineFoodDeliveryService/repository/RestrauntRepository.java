package com.example.OnlineFoodDeliveryService.repository;

import com.example.OnlineFoodDeliveryService.models.Restraunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestrauntRepository extends JpaRepository<Restraunt,Integer> {

    Restraunt findByGmail(String gmail);
}
