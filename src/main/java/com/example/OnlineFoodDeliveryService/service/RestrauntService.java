package com.example.OnlineFoodDeliveryService.service;


import com.example.OnlineFoodDeliveryService.dto.request.RestrauntRequest;
import com.example.OnlineFoodDeliveryService.exceptions.ResourceNotFoundException;
import com.example.OnlineFoodDeliveryService.models.Restraunt;
import com.example.OnlineFoodDeliveryService.repository.RestrauntRepository;
import com.example.OnlineFoodDeliveryService.transformer.RestrauntTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestrauntService {
    private final RestrauntRepository restrauntRepository;

    // add restraunt
    public String addRestruant(RestrauntRequest restrauntRequest){

        Restraunt restraunt=restrauntRepository.findByGmail(restrauntRequest.getGmail());

        if(restraunt!=null) throw new RuntimeException(String.format("The email '%s' is already registered with another restaurant. Please use a different email.",restrauntRequest.getGmail()));

        restraunt= RestrauntTransformer.restrauntRequestToRestruant(restrauntRequest);
        restrauntRepository.save(restraunt);

        return String.format("Restaurant '%s' has been successfully added to the system.", restraunt.getName());

    }


    // give rating or update rating
    public String giveOrUpdateRating(String gmail,float rating){
        Restraunt restraunt=restrauntRepository.findByGmail(gmail);

        if(restraunt==null) throw new ResourceNotFoundException(
                String.format("The email '%s' is not associated with any restaurant. Please verify the email and try again.", gmail)
        );

        // update rating
        restraunt.setRating(rating);
        restrauntRepository.save(restraunt);

        return String.format("The rating for restaurant '%s' has been successfully updated to %.1f. Thank you for your feedback!", restraunt.getName(), rating);


    }
}