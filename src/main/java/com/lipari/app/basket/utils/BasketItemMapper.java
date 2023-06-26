package com.lipari.app.basket.utils;

import com.lipari.app.basket.entities.Basket;
import com.lipari.app.basket.entities.BasketItem;
import com.lipari.app.basket.entities.BasketItemDTO;
import com.lipari.app.basket.repositories.BasketRepository;
import com.lipari.app.commons.exception.utils.DataException;
import com.lipari.app.products.entities.Product;
import com.lipari.app.products.services.ProductService;
import com.lipari.app.users.entities.User;
import com.lipari.app.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.zip.DataFormatException;

@Component
public class BasketItemMapper {

    private ProductService productService;
    private UserService userService;
    private BasketRepository basketRepository;

    @Autowired
    public BasketItemMapper(ProductService productService, UserService userService, BasketRepository basketRepository){
        this.productService = productService;
        this.userService = userService;
        this.basketRepository = basketRepository;
    }

    /**
     * Maps a BasketItemDTO object to a BasketItem entity.
     *
     * @param dto the DTO object representing the basket item
     * @return the mapped BasketItem entity
     */
    public BasketItem mapToEntity(BasketItemDTO dto) {
        Basket basket = findBasketByUserId(dto.getUserId());
        Product product = productService.getProductById(dto.getProductId());
        BasketItem basketItem = new BasketItem();
        basketItem.setBasket(basket);
        basketItem.setProduct(product);
        basketItem.setQuantity(dto.getQuantity());
        return basketItem;
    }

    /**
     * Finds the basket associated with the specified user ID.
     * If the user does not have a basket, a new basket will be created and associated with the user.
     *
     * @param userId the ID of the user
     * @return the basket associated with the user
     */
    @Transactional(rollbackFor = DataException.class)
    public Basket findBasketByUserId(Long userId) {
        User user = userService.findUserById(userId);
        Basket basket = user.getBasket();
        if (basket == null) {
            basket = new Basket();
            user.setBasket(basket);
            basketRepository.save(basket);
            userService.changeUser(user.getId(),user);
        }
        return basket;
    }
}
