package com.example.OnlineFoodDeliveryService.repository;

import com.example.OnlineFoodDeliveryService.models.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem,Integer> {


    @Query(value = "select * from menu_item where menucard_id = :id AND category= :category",nativeQuery = true)
    List<MenuItem> findMenuItemsByMenuCardIdAndCategory(@Param("id") int id,@Param("category") String category);

}
