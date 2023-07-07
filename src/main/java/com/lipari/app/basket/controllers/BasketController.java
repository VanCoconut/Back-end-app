package com.lipari.app.basket.controllers;


import com.lipari.app.basket.entities.Basket;
import com.lipari.app.basket.entities.BasketItemDTO;
import com.lipari.app.basket.services.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The type Basket controller.
 */
@RestController
@RequestMapping("/api/baskets")
public class BasketController {

    /**
     * The Basket service.
     */
    BasketService basketService;

    /**
     * Instantiates a new Basket controller.
     *
     * @param basketService the basket service
     */
    @Autowired
    public BasketController(BasketService basketService){
        this.basketService = basketService;
    }


    /**
     * Add basket item response entity.
     *
     * @param basketItemDTO the basket item dto
     * @return the response entity
     */
    @PostMapping()
    public ResponseEntity<String> addBasketItem(@RequestBody BasketItemDTO basketItemDTO){
        return ResponseEntity.ok(basketService.addBasketItem(basketItemDTO));
    }

    /**
     * Update basket item quantity.
     *
     * @param basketItemId   the basket item id
     * @param quantityChange the quantity change
     */
    @PatchMapping("/updateQuantity")
    public void updateBasketItemQuantity(@RequestParam Long basketItemId, @RequestParam int quantityChange ){
        basketService.updateBasketItemQuantity(basketItemId, quantityChange);
    }

    /**
     * Get user basket response entity.
     *
     * @param userId the user id
     * @return the response entity
     */
    @GetMapping("/{userId}")
    public ResponseEntity<Basket> getUserBasket(@PathVariable Long userId){
        return ResponseEntity.ok(basketService.getUserBasket(userId));
    }

}
