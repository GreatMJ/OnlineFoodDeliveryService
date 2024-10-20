package com.example.OnlineFoodDeliveryService.controller;


import com.example.OnlineFoodDeliveryService.dto.request.RestrauntRequest;
import com.example.OnlineFoodDeliveryService.exceptions.ResourceNotFoundException;
import com.example.OnlineFoodDeliveryService.service.RestrauntService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/restraunt")
@RequiredArgsConstructor
public class RestrauntController {

    private  final RestrauntService restrauntService;

    @PostMapping
    public ResponseEntity<String> addRestraunt(@RequestBody RestrauntRequest restrauntRequest){
        try{
            String res=restrauntService.addRestruant(restrauntRequest);
            return ResponseEntity.ok().body(res);
        }catch (RuntimeException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PatchMapping("/rating")
    public  ResponseEntity<String> giveOrUpdateRating(@RequestParam String gmail,@RequestParam float rating){
        try{
            String res=restrauntService.giveOrUpdateRating(gmail,rating);
            return ResponseEntity.ok().body(res);
        }catch (ResourceNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
