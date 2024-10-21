package com.example.OnlineFoodDeliveryService.service;

import com.example.OnlineFoodDeliveryService.dto.request.MenuItemRequest;
import com.example.OnlineFoodDeliveryService.exceptions.ResourceNotFoundException;
import com.example.OnlineFoodDeliveryService.models.MenuCard;
import com.example.OnlineFoodDeliveryService.models.MenuItem;
import com.example.OnlineFoodDeliveryService.repository.MenuCardRepository;
import com.example.OnlineFoodDeliveryService.repository.MenuItemRepository;
import com.example.OnlineFoodDeliveryService.transformer.MenuItemTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
