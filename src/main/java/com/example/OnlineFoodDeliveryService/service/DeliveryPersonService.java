package com.example.OnlineFoodDeliveryService.service;

import com.example.OnlineFoodDeliveryService.dto.request.DeliveryPersonRequest;
import com.example.OnlineFoodDeliveryService.dto.response.DeliveryPersonResponse;
import com.example.OnlineFoodDeliveryService.exceptions.ResourceNotFoundException;
import com.example.OnlineFoodDeliveryService.models.DeliveryPerson;
import com.example.OnlineFoodDeliveryService.repository.DeliveryPersonRepository;
import com.example.OnlineFoodDeliveryService.transformer.DeliveryPersonTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryPersonService {

    private  final DeliveryPersonRepository deliveryPersonRepository;

    // add delivery person
    public String addDeliveryPerson(DeliveryPersonRequest deliveryPersonRequest){
        DeliveryPerson deliveryPerson= DeliveryPersonTransformer.requestToEntity(deliveryPersonRequest);

        deliveryPersonRepository.save(deliveryPerson);
        return String.format("The delivery person %s has been successfully added.",deliveryPersonRequest.getName());
    }

    // delete delivery person
    public String deleteDeliveryPersonById(int id){
        DeliveryPerson deliveryPerson=deliveryPersonRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(String.format("Delivery person with id: %s not found.",id)));

        deliveryPersonRepository.delete(deliveryPerson);
        return String.format("The delivery person %s has been successfully deleted.",deliveryPerson.getName());
    }

    // get delivery person by id
    public DeliveryPersonResponse getDeliveryPersonById(int id){
        // fetch
        DeliveryPerson deliveryPerson=deliveryPersonRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(String.format("Delivery person with id: %s not found.",id)));
      // convert to dto
        DeliveryPersonResponse deliveryPersonResponse=DeliveryPersonTransformer.entityToResponse(deliveryPerson);

        return deliveryPersonResponse;
    }

    public List<DeliveryPersonResponse> getAllDeliveryPersons(){
        // fetch
        List<DeliveryPerson> deliveryPersonList=deliveryPersonRepository.findAll();

        // return empty list if delivery person added yet
        if(deliveryPersonList.isEmpty()) return Collections.emptyList();

        // convert to dto
        List<DeliveryPersonResponse> deliveryPersonResponseList=new ArrayList<>(deliveryPersonList.size());

        for(DeliveryPerson deliveryPerson:deliveryPersonList){
            DeliveryPersonResponse deliveryPersonResponse=DeliveryPersonTransformer.entityToResponse(deliveryPerson);
            deliveryPersonResponseList.add(deliveryPersonResponse);
        }

        return deliveryPersonResponseList;
    }
}
