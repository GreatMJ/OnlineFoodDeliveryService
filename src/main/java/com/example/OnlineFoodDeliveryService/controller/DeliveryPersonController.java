package com.example.OnlineFoodDeliveryService.controller;

import com.example.OnlineFoodDeliveryService.dto.request.DeliveryPersonRatingRequest;
import com.example.OnlineFoodDeliveryService.dto.request.DeliveryPersonRequest;
import com.example.OnlineFoodDeliveryService.dto.response.DeliveryPersonResponse;
import com.example.OnlineFoodDeliveryService.service.DeliveryPersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/delivery-person")
public class DeliveryPersonController {
    private  final DeliveryPersonService deliveryPersonService;

    @PostMapping
    public ResponseEntity<String> addDeliveryPerson(@RequestBody DeliveryPersonRequest deliveryPersonRequest){
        String res= deliveryPersonService.addDeliveryPerson(deliveryPersonRequest);
        return ResponseEntity.ok(res);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String > deleteDeliveryPersonById(@PathVariable int id){
        String res= deliveryPersonService.deleteDeliveryPersonById(id);
        return ResponseEntity.ok(res);
    }
    @GetMapping("/{id}")
    public  ResponseEntity<DeliveryPersonResponse> getDeliveryPersonById(@PathVariable int id){
        DeliveryPersonResponse deliveryPersonResponse=deliveryPersonService.getDeliveryPersonById(id);
        return ResponseEntity.ok(deliveryPersonResponse);
    }

    @GetMapping()
    public  ResponseEntity<?> getAllDeliveryPersons(){
        List<DeliveryPersonResponse> deliveryPersonResponseList=deliveryPersonService.getAllDeliveryPersons();

        // list empty
        if(deliveryPersonResponseList.isEmpty()) return ResponseEntity.ok("No delivery person found.");

        return ResponseEntity.ok(deliveryPersonResponseList);
    }

    @PatchMapping("/{id}/rating")
    public ResponseEntity<String> giveRating(@PathVariable int id, @RequestBody DeliveryPersonRatingRequest ratingRequest){
        String res=deliveryPersonService.giveRatingToDeliveryPerson(id,ratingRequest);
        return ResponseEntity.ok(res);
    }
}
