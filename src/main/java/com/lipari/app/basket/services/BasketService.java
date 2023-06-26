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
    @Transactional(rollbackFor = DataException.class)
    private void handleNewBasketItem(BasketItem basketItem, int requestedQuantity, Product product) {
        if(requestedQuantity < 0) {throw new InvalidDataException("The quantity may not be less than 0");}
            productService.updateProductQuantity(product.getId(), (product.getQuantity() - requestedQuantity));
            basketItemRepository.save(basketItem);
    }
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
    public Basket getUserBasket(Long userId) {
        return basketItemMapper.findBasketByUserId(userId);
    }
    public String deleteUserBasket(Long userId) {
        Long idBasket = basketIdByUserId(userId);
        if(idBasket == null ){
            throw new InvalidDataException("Basket is already empty");
        }else{
            basketRepository.deleteById(idBasket);
            return "Basket clear";
        }
    }
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
    private Long basketIdByUserId(Long userId) {
        User user = userService.findUserById(userId);
        return user.getBasket().getId();
    }
}

