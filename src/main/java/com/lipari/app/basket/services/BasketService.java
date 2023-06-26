package com.lipari.app.basket.services;

import com.lipari.app.basket.entities.Basket;
import com.lipari.app.basket.entities.BasketItem;
import com.lipari.app.basket.entities.BasketItemDTO;
import com.lipari.app.basket.repositories.BasketItemRepository;
import com.lipari.app.basket.repositories.BasketRepository;
import com.lipari.app.basket.utils.BasketItemMapper;
import com.lipari.app.commons.exception.utils.DataException;
import com.lipari.app.commons.exception.utils.InvalidDataException;
import com.lipari.app.commons.exception.utils.NotFoundException;
import com.lipari.app.products.entities.Product;
import com.lipari.app.products.services.ProductService;
import com.lipari.app.users.entities.User;
import com.lipari.app.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BasketService {

    BasketRepository basketRepository;
    BasketItemMapper basketItemMapper;
    ProductService productService;
    BasketItemRepository basketItemRepository;
    UserService userService;

    @Autowired
    public BasketService(BasketRepository basketRepository, BasketItemMapper basketItemMapper, ProductService productService, BasketItemRepository basketItemRepository, UserService userService){
        this.basketRepository = basketRepository;
        this.basketItemMapper = basketItemMapper;
        this.productService = productService;
        this.basketItemRepository = basketItemRepository;
        this.userService = userService;
    }

    /**
     * Adds a basket item to the user's basket.
     *
     * @param basketItemDTO the DTO object representing the basket item to be added
     * @return a success message indicating that the basket item has been added successfully
     * @throws InvalidDataException if the requested quantity is greater than the available quantity
     */
    @Transactional(rollbackFor = DataException.class)
    public String addBasketItem(BasketItemDTO basketItemDTO) {
        BasketItem basketItem = basketItemMapper.mapToEntity(basketItemDTO);
        Product product = basketItem.getProduct();
        int requestedQuantity = basketItem.getQuantity();
        int availableQuantity = product.getQuantity();
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
     * Handles the addition of a new basket item to the user's basket.
     *
     * @param basketItem        the basket item to be added
     * @param requestedQuantity the requested quantity of the basket item
     * @param product           the product associated with the basket item
     * @throws InvalidDataException if the requested quantity is less than 0
     */
    @Transactional(rollbackFor = DataException.class)
    private void handleNewBasketItem(BasketItem basketItem, int requestedQuantity, Product product) {
        if(requestedQuantity < 0) {throw new InvalidDataException("The quantity may not be less than 0");}
            productService.updateProductQuantity(product.getId(), (product.getQuantity() - requestedQuantity));
            basketItemRepository.save(basketItem);
    }

    /**
     * Updates the quantity of a basket item.
     *
     * @param basketItemId   the ID of the basket item to be updated
     * @param quantityChange the change in quantity for the basket item
     * @throws NotFoundException    if the basket item is not found
     * @throws InvalidDataException if the quantity change is invalid or there is insufficient quantity available
     */
    @Transactional(rollbackFor = DataException.class)
    public void updateBasketItemQuantity(Long basketItemId, int quantityChange) {
        BasketItem basketItem = basketItemRepository.findById(basketItemId).orElseThrow(() -> new NotFoundException("Basket Item not found"));
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
     * Retrieves the user's basket based on the user ID.
     *
     * @param userId the ID of the user
     * @return the user's basket
     */
    public Basket getUserBasket(Long userId) {
        return basketItemMapper.findBasketByUserId(userId);
    }

    /**
     * Deletes the user's basket.
     *
     * @param userId the ID of the user
     * @return a success message indicating that the basket has been cleared
     * @throws InvalidDataException if the basket is already empty
     */
    public String deleteUserBasket(Long userId) {
        Long idBasket = basketIdByUserId(userId);
        if(idBasket == null ){
            throw new InvalidDataException("Basket is already empty");
        }else{
            basketRepository.deleteById(idBasket);
            return "Basket clear";
        }
    }

    /**
     * Deletes a basket item from the user's basket.
     *
     * @param userId       the ID of the user
     * @param basketItemId the ID of the basket item to be deleted
     * @return a success message indicating that the basket item has been deleted successfully
     * @throws InvalidDataException if the basket is empty
     * @throws NotFoundException    if the basket item is not found in the user's basket
     */
    public String deleteUserBasketItem(Long userId, Long basketItemId) {
        Long basketId = basketIdByUserId(userId);
        Basket basket = basketRepository.findById(basketId).orElseThrow(() -> new InvalidDataException("Basket is empty"));
        BasketItem basketItem = basketItemRepository.findById(basketItemId)
                .orElseThrow(() -> new NotFoundException("Basket item not found"));
        if (!basket.getBasketItems().contains(basketItem)) {
            throw new NotFoundException("Basket item not found in the user's basket");
        }
        Product product = basketItem.getProduct();
        int basketItemQuantity = basketItem.getQuantity();
        basket.getBasketItems().remove(basketItem);
        basketItemRepository.delete(basketItem);
        productService.updateProductQuantity(product.getId(), product.getQuantity() + basketItemQuantity);
        return "Basket item deleted successfully";
    }

    /**
     * Retrieves the ID of the user's basket based on the user ID.
     *
     * @param userId the ID of the user
     * @return the ID of the user's basket
     */
    private Long basketIdByUserId(Long userId) {
        User user = userService.findUserById(userId);
        return user.getBasket().getId();
    }
}

