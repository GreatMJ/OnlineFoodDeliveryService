package com.example.OnlineFoodDeliveryService.repository;

import com.example.OnlineFoodDeliveryService.models.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem,Integer> {


    @Query(value = "select * from menu_item where menucard_id = :id AND category= :category",nativeQuery = true)
    List<MenuItem> findMenuItemsByMenuCardIdAndCategory(@Param("id") int id,@Param("category") String category);


    @Query(value = "select * from menu_item where menucard_id = :menuCardId AND id= :menuItemId",nativeQuery = true)
   Optional<MenuItem >getMenuItemFromMenuCardById(@Param("menuCardId") int menuCardId, @Param("menuItemId") int menuItemId);


}
