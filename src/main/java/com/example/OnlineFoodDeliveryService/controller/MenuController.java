package com.example.OnlineFoodDeliveryService.controller;


import com.example.OnlineFoodDeliveryService.dto.request.MenuItemRequest;
import com.example.OnlineFoodDeliveryService.dto.response.MenuItemResponse;
import com.example.OnlineFoodDeliveryService.exceptions.ResourceNotFoundException;
import com.example.OnlineFoodDeliveryService.service.MenuCardService;
import com.example.OnlineFoodDeliveryService.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/menu")
public class MenuController {

    private final MenuCardService menuCardService;
    private  final MenuItemService menuItemService;
    @PostMapping("/menucard")
    public ResponseEntity<String> addMenuCard(@RequestParam String restrauntGmail){

            String res= menuCardService.addMenuCard(restrauntGmail);
            return ResponseEntity.ok(res);

    }

    @PostMapping("/menu-item")
    public  ResponseEntity<String> addMenuItem(@RequestBody MenuItemRequest menuItemRequest){

            String res=menuItemService.addMenuItem(menuItemRequest);
            return ResponseEntity.ok(res);

    }


    @GetMapping("/{menuCardId}/menu-items")
    public ResponseEntity<List<MenuItemResponse>> getMenuItemsByMenuCardId(@PathVariable int menuCardId){

            List<MenuItemResponse> menuItemResponseList=menuItemService.getMenuItemsByMenuCardId(menuCardId);
            return ResponseEntity.ok(menuItemResponseList);

    }

   @GetMapping("/{menuCardId}/menu-items/category/{category}")
    public  ResponseEntity<   List<MenuItemResponse>> getMenuItemsByCategory(@PathVariable int menuCardId,@PathVariable String category){

            List<MenuItemResponse> menuItemResponseList=menuCardService.getMenuItemsByCategory(menuCardId,category);
            return ResponseEntity.ok(menuItemResponseList);

   }

   @DeleteMapping("/menu-card/{menuCardId}/menu-item/{menuItemId}")
    public ResponseEntity<String> deleteMenuItemFromMenuCard(@PathVariable int menuCardId,@PathVariable int menuItemId){

            menuItemService.deleteMenuItemFromMenuCard(menuCardId,menuItemId);
            return ResponseEntity.ok("Menuitem deleted successfully.");

   }

}
