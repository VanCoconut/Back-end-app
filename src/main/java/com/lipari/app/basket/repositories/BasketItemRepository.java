package com.lipari.app.basket.repositories;

import com.lipari.app.basket.entities.Basket;
import com.lipari.app.basket.entities.BasketItem;
import com.lipari.app.products.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Basket item repository.
 */
@Repository
public interface BasketItemRepository extends JpaRepository<BasketItem, Long> {
    /**
     * Find by basket id and product id basket item.
     *
     * @param basketId  the basket id
     * @param productId the product id
     * @return the basket item
     */
    BasketItem findByBasketIdAndProductId(Long basketId, Long productId);

    /**
     * Find by basket and product basket item.
     *
     * @param basket  the basket
     * @param product the product
     * @return the basket item
     */
    BasketItem findByBasketAndProduct(Basket basket, Product product);
}
