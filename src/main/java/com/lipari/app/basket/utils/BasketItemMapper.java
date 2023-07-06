package com.lipari.app.basket.utils;

import com.lipari.app.basket.entities.Basket;
import com.lipari.app.basket.entities.BasketItem;
import com.lipari.app.basket.entities.BasketItemDTO;
import com.lipari.app.basket.repositories.BasketRepository;
import com.lipari.app.basket.services.BasketService;
import com.lipari.app.commons.exception.utils.InvalidDataException;
import com.lipari.app.commons.exception.utils.NotFoundException;
import com.lipari.app.products.entities.Product;
import com.lipari.app.products.repositories.ProductRepository;
import com.lipari.app.products.services.ProductService;
import com.lipari.app.users.entities.AppUser;
import com.lipari.app.users.services.UserService;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public BasketItem mapToEntity(BasketItemDTO dto) {
        Basket basket = findBasketByUserId(dto.getUserId());
        Product product = productService.getProductById(dto.getProductId());
        BasketItem basketItem = new BasketItem();
        basketItem.setBasket(basket);
        basketItem.setProduct(product);
        basketItem.setQuantity(dto.getQuantity());
        return basketItem;
    }

    public Basket findBasketByUserId(Long userId) {
        AppUser user = userService.findUserById(userId);
        Basket basket = user.getBasket();
        if (basket == null) {
            basket = new Basket();
            basketRepository.save(basket);
            user.setBasket(basket);
            userService.changeUser(user.getId(),user);
        }
        return basket;
    }
}
