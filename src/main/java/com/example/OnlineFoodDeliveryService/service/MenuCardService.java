package com.example.OnlineFoodDeliveryService.service;

import com.example.OnlineFoodDeliveryService.exceptions.ResourceNotFoundException;
import com.example.OnlineFoodDeliveryService.models.MenuCard;
import com.example.OnlineFoodDeliveryService.models.Restraunt;
import com.example.OnlineFoodDeliveryService.repository.MenuCardRepository;
import com.example.OnlineFoodDeliveryService.repository.RestrauntRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuCardService {

    private final MenuCardRepository menuCardRepository;
    private final RestrauntRepository restrauntRepository;
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
}
