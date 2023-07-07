package com.lipari.app.basket.utils;

import com.lipari.app.basket.entities.Basket;
import com.lipari.app.basket.entities.BasketItem;
import com.lipari.app.basket.entities.BasketItemDTO;
import com.lipari.app.basket.repositories.BasketRepository;
import com.lipari.app.products.entities.Product;
import com.lipari.app.products.services.ProductService;
import com.lipari.app.users.entities.AppUser;
import com.lipari.app.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The type Basket item mapper.
 */
@Component
public class BasketItemMapper {

    /**
     * The Product service.
     */
    private ProductService productService;
    /**
     * The User service.
     */
    private UserService userService;
    /**
     * The Basket repository.
     */
    private BasketRepository basketRepository;

    /**
     * Instantiates a new Basket item mapper.
     *
     * @param productService   the product service
     * @param userService      the user service
     * @param basketRepository the basket repository
     */
    @Autowired
    public BasketItemMapper(ProductService productService, UserService userService, BasketRepository basketRepository){
        this.productService = productService;
        this.userService = userService;
        this.basketRepository = basketRepository;
    }

    /**
     * Map to entity basket item.
     *
     * @param dto the dto
     * @return the basket item
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
     * Find basket by user id basket.
     *
     * @param userId the user id
     * @return the basket
     */
    public Basket findBasketByUserId(Long userId) {
        AppUser appUser = userService.findUserById(userId);
        Basket basket = appUser.getBasket();
        if (basket == null) {
            basket = new Basket();
            basketRepository.save(basket);
            appUser.setBasket(basket);
            userService.changeUser(appUser.getId(), appUser);
        }
        return basket;
    }
}
