package com.example.OnlineFoodDeliveryService.repository;

import com.example.OnlineFoodDeliveryService.models.Restraunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestrauntRepository extends JpaRepository<Restraunt,Integer> {

    Restraunt findByGmail(String gmail);

    @Query(value = "select * from restraunt where rating>= :rating",nativeQuery = true)
    List<Restraunt> findRestrauntsWithRatingGreaterThan(@Param("rating") float rating);
}
