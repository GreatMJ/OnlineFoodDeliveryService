package com.example.OnlineFoodDeliveryService.service;

import com.example.OnlineFoodDeliveryService.dto.request.MenuItemRequest;
import com.example.OnlineFoodDeliveryService.dto.response.MenuItemResponse;
import com.example.OnlineFoodDeliveryService.exceptions.ResourceNotFoundException;
import com.example.OnlineFoodDeliveryService.models.MenuCard;
import com.example.OnlineFoodDeliveryService.models.MenuItem;
import com.example.OnlineFoodDeliveryService.repository.MenuCardRepository;
import com.example.OnlineFoodDeliveryService.repository.MenuItemRepository;
import com.example.OnlineFoodDeliveryService.transformer.MenuItemTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;
     private final MenuCardRepository menuCardRepository;

    // add menuItem to menuCard

    public String addMenuItem(MenuItemRequest menuItemRequest){
        MenuCard menuCard=menuCardRepository.findById(menuItemRequest.getMenuCardId()).orElseThrow(()-> new ResourceNotFoundException(String.format("MenuCard not found.")));

        MenuItem menuItem= MenuItemTransformer.menuItemRequestToMenuItem(menuItemRequest,menuCard);
       menuCard.addMenuItem(menuItem);
        menuCardRepository.save(menuCard);
        return String.format("%s is successfully added in menucard of %s.",menuItem.getName(),menuCard.getRestraunt().getName());
    }

    // get the list of menu items present in respective menu card
    public List<MenuItemResponse> getMenuItemsByMenuCardId(int menuCardId){
        MenuCard menuCard=menuCardRepository.findById(menuCardId).orElseThrow(()->new ResourceNotFoundException("MenuCard not found."));
        // Initialize an empty list to store the transformed MenuItemResponse objects.
        List<MenuItemResponse> menuItemResponseList=new ArrayList<>();
        // Get the list of MenuItems from the fetched MenuCard.
     List<MenuItem> menuItemsList=menuCard.getMenuItemList();
        // Iterate through each MenuItem in the list, convert it to MenuItemResponse, and add it to the response list.
     for(MenuItem menuItem:menuItemsList){
         MenuItemResponse menuItemResponse=MenuItemTransformer.menuItemToMenuItemResponse(menuItem);

         // Add the transformed response to the list.
         menuItemResponseList.add(menuItemResponse);
     }
     return  menuItemResponseList;


    }
}
