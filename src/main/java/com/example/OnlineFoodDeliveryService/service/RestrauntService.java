package com.example.OnlineFoodDeliveryService.service;


import com.example.OnlineFoodDeliveryService.dto.request.RestrauntRequest;
import com.example.OnlineFoodDeliveryService.dto.response.RestrauntResponse;
import com.example.OnlineFoodDeliveryService.exceptions.ResourceNotFoundException;
import com.example.OnlineFoodDeliveryService.models.Restraunt;
import com.example.OnlineFoodDeliveryService.repository.RestrauntRepository;
import com.example.OnlineFoodDeliveryService.transformer.RestrauntTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


    // get restraunts with rating greater than X
    public List<RestrauntResponse> findRestrauntsByRatingGreaterThan(float requiredRating){
        // check if required rating  is in proper range
        if(requiredRating<0||requiredRating>10) throw new IllegalArgumentException("Rating must be between 0 to 10. Provided rating: "+requiredRating);

       // fetch restraunts
        List<Restraunt>restrauntList=restrauntRepository.findRestrauntsWithRatingGreaterThan(requiredRating);

        // if no restraunt available
        if(restrauntList==null||restrauntList.isEmpty()) throw new ResourceNotFoundException("No restraunt found with rating greater than " + requiredRating +".");

        // initialize list for response
        List<RestrauntResponse>restrauntResponseList=new ArrayList<>(restrauntList.size());

        // conver restraunt to dto response
        for(Restraunt restraunt:restrauntList){
            RestrauntResponse restrauntResponse=RestrauntTransformer.restrauntToRestrauntResponse(restraunt);
            restrauntResponseList.add(restrauntResponse);
        }

        return restrauntResponseList;
    }


    // delete a restraunt by id

    public void deleteRestrauntById(int id){
       Restraunt restraunt=restrauntRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Restraunt not found with id: "+id+"."));

       restrauntRepository.delete(restraunt);

       return;

    }
}