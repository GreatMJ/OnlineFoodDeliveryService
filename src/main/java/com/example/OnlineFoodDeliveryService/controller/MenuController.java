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
        try{
            String res= menuCardService.addMenuCard(restrauntGmail);
            return ResponseEntity.ok().body(res);
        }catch (ResourceNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }catch (IllegalStateException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/menu-item")
    public  ResponseEntity<String> addMenuItem(@RequestBody MenuItemRequest menuItemRequest){
        try{
            String res=menuItemService.addMenuItem(menuItemRequest);
            return ResponseEntity.ok().body(res);
        }catch (ResourceNotFoundException ex){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }


    @GetMapping("/{menuCardId}/menu-items")
    public ResponseEntity<?> getMenuItemsByMenuCardId(@PathVariable int menuCardId){
        try{
            List<MenuItemResponse> menuItemResponseList=menuItemService.getMenuItemsByMenuCardId(menuCardId);
            return ResponseEntity.ok().body(menuItemResponseList);
        }catch (ResourceNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

}
