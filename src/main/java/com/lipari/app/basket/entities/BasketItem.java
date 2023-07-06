package com.lipari.app.basket.entities;

import com.lipari.app.products.entities.Product;
import javax.persistence.*;
;

/**
 * The type Basket item.
 */
@Entity
@Table(name = "t_basket_item")
public class BasketItem {
    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The Basket.
     */
    @ManyToOne
    @JoinColumn(name = "basket_id")
    private Basket basket;

    /**
     * The Product.
     */
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * The Quantity.
     */
    @Column(name = "quantity")
    private int quantity;

    /**
     * Instantiates a new Basket item.
     */
    public BasketItem() {
    }

    /**
     * Instantiates a new Basket item.
     *
     * @param basket   the basket
     * @param product  the product
     * @param quantity the quantity
     */
    public BasketItem(Basket basket, Product product, int quantity) {
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets basket.
     *
     * @return the basket
     */
    public Basket getBasket() {
        return basket;
    }

    /**
     * Sets basket.
     *
     * @param basket the basket
     */
    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    /**
     * Gets product.
     *
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets product.
     *
     * @param product the product
     */
    public void setProduct(Product product) {
        this.product = product;
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
}
