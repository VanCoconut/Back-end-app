package com.lipari.app.basket.controllers;


import com.lipari.app.basket.entities.Basket;
import com.lipari.app.basket.entities.BasketItemDTO;
import com.lipari.app.basket.services.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/baskets")
public class BasketController {

    BasketService basketService;

    @Autowired
    public BasketController(BasketService basketService){
        this.basketService = basketService;
    }


    @PostMapping()
    public ResponseEntity<String> addBasketItem(@RequestBody BasketItemDTO basketItemDTO){
        return ResponseEntity.ok(basketService.addBasketItem(basketItemDTO));
    }

    @PatchMapping("/items/quantity")
    public void updateBasketItemQuantity(@RequestParam Long basketItemId, @RequestParam int quantityChange ){
        basketService.updateBasketItemQuantity(basketItemId, quantityChange);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Basket> getUserBasket(@PathVariable Long userId){
        return ResponseEntity.ok(basketService.getUserBasket(userId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUserBasket(@PathVariable Long userId){
        return ResponseEntity.ok(basketService.deleteUserBasket(userId));
    }

    @DeleteMapping("/items")
    public ResponseEntity<String> deleteUserBasketItem(@RequestParam Long userId, @RequestParam Long basketItem){
        return ResponseEntity.ok(basketService.deleteUserBasketItem(userId, basketItem));
    }
}
