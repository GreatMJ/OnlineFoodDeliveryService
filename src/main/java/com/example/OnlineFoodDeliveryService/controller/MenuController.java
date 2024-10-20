package com.example.OnlineFoodDeliveryService.controller;


import com.example.OnlineFoodDeliveryService.exceptions.ResourceNotFoundException;
import com.example.OnlineFoodDeliveryService.service.MenuCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/menu")
public class MenuController {

    private final MenuCardService menuCardService;

    @PostMapping("/menucard")
    public ResponseEntity<String> addMenuCard(@RequestParam String restrauntGmail){
        try{
            String res= menuCardService.addMenuCard(restrauntGmail);
            return ResponseEntity.ok().body(res);
        }catch (ResourceNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }catch (IllegalStateException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
