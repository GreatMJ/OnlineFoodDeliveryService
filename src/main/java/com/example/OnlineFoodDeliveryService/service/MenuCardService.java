package com.example.OnlineFoodDeliveryService.service;

import com.example.OnlineFoodDeliveryService.dto.response.MenuItemResponse;
import com.example.OnlineFoodDeliveryService.exceptions.ResourceNotFoundException;
import com.example.OnlineFoodDeliveryService.models.MenuCard;
import com.example.OnlineFoodDeliveryService.models.MenuItem;
import com.example.OnlineFoodDeliveryService.models.Restraunt;
import com.example.OnlineFoodDeliveryService.repository.MenuCardRepository;
import com.example.OnlineFoodDeliveryService.repository.MenuItemRepository;
import com.example.OnlineFoodDeliveryService.repository.RestrauntRepository;
import com.example.OnlineFoodDeliveryService.transformer.MenuItemTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuCardService {

    private final MenuCardRepository menuCardRepository;
    private final RestrauntRepository restrauntRepository;
    private final MenuItemRepository menuItemRepository;


    // add menu card
    public String addMenuCard(String restrauntGmail){
        Restraunt restraunt=restrauntRepository.findByGmail(restrauntGmail);
        if(restraunt==null) throw new ResourceNotFoundException(
                String.format("The email '%s' is not associated with any restaurant. Please verify the email and try again.", restrauntGmail)
        );

        if(restraunt.getMenuCard()!=null) throw  new IllegalStateException(String.format("MenuCard already exists for the restaurant with email '%s'.", restrauntGmail)
        );


        MenuCard menuCard=new MenuCard();
        menuCard.setRestraunt(restraunt);
        restraunt.setMenuCard(menuCard);
       restrauntRepository.save(restraunt);

        return String.format("MenuCard for '%s' has been successfully created.", restraunt.getName());
    }





    // get menuItems under given category
    public List<MenuItemResponse> getMenuItemsByCategory(int menuCardId,String category){
        // first check if menu card present with given id or not
      MenuCard  menuCard=menuCardRepository.findById(menuCardId).orElseThrow(()-> new ResourceNotFoundException(String.format("MenuCard with id %d not found.",menuCardId)));

      // trim the category
        String trimmedCategory=category.trim();
        if(trimmedCategory.isEmpty()) throw new IllegalArgumentException("Category must not be empty.");

        // menuitems from repostiory by given category
        List<MenuItem> menuItemList=menuItemRepository.findMenuItemsByMenuCardIdAndCategory(menuCardId,category);

        // if no menu items found
        if(menuItemList==null||menuItemList.isEmpty()) throw new ResourceNotFoundException(String.format("No menuitems found for category %s under menucard id %d.",trimmedCategory,menuCardId));
        // initialize the list
        List<MenuItemResponse> menuItemResponseList=new ArrayList<>(menuItemList.size());

        // convert menuItem to response dto
        for(MenuItem menuItem:menuItemList){
            MenuItemResponse menuItemResponse= MenuItemTransformer.menuItemToMenuItemResponse(menuItem);
            menuItemResponseList.add(menuItemResponse);
        }

        return menuItemResponseList;
    }

}
