package com.example.OnlineFoodDeliveryService.service;

import com.example.OnlineFoodDeliveryService.dto.request.DeliveryPersonRatingRequest;
import com.example.OnlineFoodDeliveryService.dto.request.DeliveryPersonRequest;
import com.example.OnlineFoodDeliveryService.dto.response.DeliveryPersonResponse;
import com.example.OnlineFoodDeliveryService.exceptions.ResourceNotFoundException;
import com.example.OnlineFoodDeliveryService.models.Customer;
import com.example.OnlineFoodDeliveryService.models.Delivery;
import com.example.OnlineFoodDeliveryService.models.DeliveryPerson;

import com.example.OnlineFoodDeliveryService.repository.CustomerRepository;
import com.example.OnlineFoodDeliveryService.repository.DeliveryPersonRepository;

import com.example.OnlineFoodDeliveryService.repository.DeliveryRepository;
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
    private final CustomerRepository customerRepository;
    private final DeliveryRepository deliveryRepository;

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

    // get All delivery persons
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


    //////////////////////////////////////
    // DELIVERY PERSON RATING METHOD IS IN PROCESS
    // give rating to delivery person

//    public String giveRatingToDeliveryPerson(int id, DeliveryPersonRatingRequest ratingRequest){
//        // first check customer
//        Customer ratingCustomer=customerRepository.findById(ratingRequest.getCustomerId()).orElseThrow(()->new ResourceNotFoundException("Customer not found."));
//        // check if order exist
//        Delivery delivery=deliveryRepository.findById(ratingRequest.getDeliveryId()).orElseThrow(()->new ResourceNotFoundException("Delivery not found."));
//        //check if the delivery is associated with delivery person
//    };
}
