package com.example.OnlineFoodDeliveryService.transformer;

import com.example.OnlineFoodDeliveryService.dto.request.RestrauntRequest;
import com.example.OnlineFoodDeliveryService.dto.response.RestrauntResponse;
import com.example.OnlineFoodDeliveryService.models.Restraunt;

public class RestrauntTransformer {

    public static Restraunt restrauntRequestToRestruant(RestrauntRequest restrauntRequest){
        return Restraunt.builder()
                .address(restrauntRequest.getAddress())
                .name(restrauntRequest.getName())
                .contactNo(restrauntRequest.getContactNo())
                .gmail(restrauntRequest.getGmail())
                .rating(restrauntRequest.getRating())
                        .build();
    }

    public static RestrauntResponse restrauntToRestrauntResponse(Restraunt restraunt){
        return RestrauntResponse.builder()
                .name(restraunt.getName())
                .address(restraunt.getAddress())
                .contactNo(restraunt.getContactNo())
                .rating(restraunt.getRating())
                .gmail(restraunt.getGmail())
                                .build();
    }
}
