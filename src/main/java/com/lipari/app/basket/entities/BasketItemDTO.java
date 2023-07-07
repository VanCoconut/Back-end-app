package com.lipari.app.basket.entities;

/**
 * The type Basket item dto.
 */
public class BasketItemDTO {
    /**
     * The User id.
     */
    private Long userId;
    /**
     * The Product id.
     */
    private Long productId;
    /**
     * The Quantity.
     */
    private int quantity;

    /**
     * Instantiates a new Basket item dto.
     */
    public BasketItemDTO() {
    }

    /**
     * Instantiates a new Basket item dto.
     *
     * @param userId    the user id
     * @param productId the product id
     * @param quantity  the quantity
     */
    public BasketItemDTO(Long userId, Long productId, int quantity) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

    /**
     * Gets product id.
     *
     * @return the product id
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * Sets product id.
     *
     * @param productId the product id
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets quantity.
     *
     * @param quantity the quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
