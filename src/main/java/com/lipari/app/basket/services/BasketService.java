package com.lipari.app.basket.services;

import com.lipari.app.basket.entities.Basket;
import com.lipari.app.basket.entities.BasketItem;
import com.lipari.app.basket.entities.BasketItemDTO;
import com.lipari.app.basket.repositories.BasketItemRepository;
import com.lipari.app.basket.repositories.BasketRepository;
import com.lipari.app.basket.utils.BasketItemMapper;
import com.lipari.app.commons.exception.utils.InvalidDataException;
import com.lipari.app.products.entities.Product;
import com.lipari.app.products.services.ProductService;
import com.lipari.app.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Basket service.
 */
@Service
public class BasketService {

    /**
     * The Basket repository.
     */
    BasketRepository basketRepository;
    /**
     * The Basket item mapper.
     */
    BasketItemMapper basketItemMapper;
    /**
     * The Product service.
     */
    ProductService productService;
    /**
     * The Basket item repository.
     */
    BasketItemRepository basketItemRepository;

    /**
     * The User service.
     */
    UserService userService;

    /**
     * Instantiates a new Basket service.
     *
     * @param basketRepository     the basket repository
     * @param basketItemMapper     the basket item mapper
     * @param productService       the product service
     * @param basketItemRepository the basket item repository
     * @param userService          the user service
     */
    @Autowired
    public BasketService(BasketRepository basketRepository, BasketItemMapper basketItemMapper, ProductService productService, BasketItemRepository basketItemRepository, UserService userService){
        this.basketRepository = basketRepository;
        this.basketItemMapper = basketItemMapper;
        this.productService = productService;
        this.basketItemRepository = basketItemRepository;
        this.userService = userService;
    }

    /**
     * Add basket item string.
     *
     * @param basketItemDTO the basket item dto
     * @return the string
     */
    @Transactional
    public String addBasketItem(BasketItemDTO basketItemDTO) {
        BasketItem basketItem = basketItemMapper.mapToEntity(basketItemDTO);
        Product product = basketItem.getProduct();
        int requestedQuantity = basketItem.getQuantity();
        int availableQuantity = productService.checkAvailableQuantity(product.getId());
        BasketItem existingBasketItem = basketItemRepository.findByBasketAndProduct(basketItem.getBasket(), product);

        if (requestedQuantity <= availableQuantity) {

            if (existingBasketItem != null) {
                updateBasketItemQuantity(existingBasketItem.getId(), requestedQuantity);
            } else {
                handleNewBasketItem(basketItem, requestedQuantity, product);
            }

        } else {
            throw new InvalidDataException("Insufficient quantity available");
        }
        return "Basket item added successfully";
    }

    /**
     * Handle new basket item.
     *
     * @param basketItem        the basket item
     * @param requestedQuantity the requested quantity
     * @param product           the product
     */
    private void handleNewBasketItem(BasketItem basketItem, int requestedQuantity, Product product) {
            productService.updateProductQuantity(product.getId(), (product.getQuantity() - requestedQuantity));
            basketItemRepository.save(basketItem);
    }

    /**
     * Update basket item quantity.
     *
     * @param basketItemId   the basket item id
     * @param quantityChange the quantity change
     */
    @Transactional
    public void updateBasketItemQuantity(Long basketItemId, int quantityChange) {
        BasketItem basketItem = basketItemRepository.findById(basketItemId).orElseThrow();
            int currentQuantity = basketItem.getQuantity();
            int updatedQuantity = currentQuantity + quantityChange;

            if (updatedQuantity >= 0 && quantityChange <= basketItem.getProduct().getQuantity()) {
                basketItem.setQuantity(updatedQuantity);
                basketItemRepository.save(basketItem);

                Product product = basketItem.getProduct();
                int productQuantity = product.getQuantity() - quantityChange;
                product.setQuantity(productQuantity);
                productService.updateProductQuantity(product.getId(), productQuantity);
            } else {
                throw new InvalidDataException("Invalid quantity change or insufficient quantity available");
            }
        }

    /**
     * Gets user basket.
     *
     * @param userId the user id
     * @return the user basket
     */
    public Basket getUserBasket(Long userId) {
        return basketItemMapper.findBasketByUserId(userId);
    }
}

