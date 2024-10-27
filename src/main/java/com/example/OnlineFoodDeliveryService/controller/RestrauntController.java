package com.example.OnlineFoodDeliveryService.controller;


import com.example.OnlineFoodDeliveryService.dto.request.RestrauntRequest;
import com.example.OnlineFoodDeliveryService.dto.response.RestrauntResponse;

import com.example.OnlineFoodDeliveryService.service.RestrauntService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/restraunt")
@RequiredArgsConstructor
public class RestrauntController {

    private  final RestrauntService restrauntService;

    @PostMapping
    public ResponseEntity<String> addRestraunt(@RequestBody RestrauntRequest restrauntRequest){

            String res=restrauntService.addRestruant(restrauntRequest);
            return ResponseEntity.ok().body(res);

    }

    @PatchMapping("/rating")
    public  ResponseEntity<String> giveOrUpdateRating(@RequestParam String gmail,@RequestParam float rating){

            String res=restrauntService.giveOrUpdateRating(gmail,rating);
            return ResponseEntity.ok().body(res);

    }

    @GetMapping("/rating/greater-than/{rating}")
    public  ResponseEntity< List<RestrauntResponse>> findRestrauntsWithRatingGreaterThan(@PathVariable float rating){

            List<RestrauntResponse> restrauntResponseList=restrauntService.findRestrauntsByRatingGreaterThan(rating);
            return ResponseEntity.ok().body(restrauntResponseList);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRestrauntById(@PathVariable int id){

            restrauntService.deleteRestrauntById(id);
            return ResponseEntity.ok("Restraunt deleted successfully.");

    }


}
