package com.example.OnlineFoodDeliveryService.transformer;

import com.example.OnlineFoodDeliveryService.dto.request.MenuItemRequest;
import com.example.OnlineFoodDeliveryService.dto.response.MenuItemResponse;
import com.example.OnlineFoodDeliveryService.models.MenuCard;
import com.example.OnlineFoodDeliveryService.models.MenuItem;

public class MenuItemTransformer {

    public  static MenuItem menuItemRequestToMenuItem(MenuItemRequest menuItemRequest, MenuCard menuCard){
        return MenuItem.builder()
                .name(menuItemRequest.getName())
                .category(menuItemRequest.getCategory())
                .price(menuItemRequest.getPrice())
                .menuCard(menuCard)
                       .build();
    }

    public static MenuItemResponse menuItemToMenuItemResponse(MenuItem menuItem){
        return MenuItemResponse.builder()
                .name(menuItem.getName())
                .category(menuItem.getCategory())
                .price(menuItem.getPrice())
                               .build();
    }
}
