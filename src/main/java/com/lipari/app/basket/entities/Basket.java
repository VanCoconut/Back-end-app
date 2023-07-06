package com.lipari.app.basket.entities;

import com.lipari.app.products.entities.Product;
import com.lipari.app.users.entities.Address;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Basket.
 */
@Entity
@Table(name="t_basket")
public class Basket {
    /**
     * The Id.
     */
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    /**
     * The Basket items.
     */
    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL)
	private List<BasketItem> basketItems;

    /**
     * Instantiates a new Basket.
     */
    public Basket() {
	}

    /**
     * Instantiates a new Basket.
     *
     * @param id          the id
     * @param basketItems the basket items
     */
    public Basket(Long id, List<BasketItem> basketItems) {
		this.id = id;
		this.basketItems = basketItems;
	}

    /**
     * Instantiates a new Basket.
     *
     * @param basketId the basket id
     */
    public Basket(Long basketId) {
	}

	@Override
	public String toString() {
		return "Basket{" +
				"id=" + id +
				", basketItems=" + basketItems +
				'}';
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
     * Gets basket items.
     *
     * @return the basket items
     */
    public List<BasketItem> getBasketItems() {
		return basketItems;
	}

    /**
     * Sets basket items.
     *
     * @param basketItems the basket items
     */
    public void setBasketItems(List<BasketItem> basketItems) {
		this.basketItems = basketItems;
	}

    /**
     * Add item.
     *
     * @param basketItem the basket item
     */
    public void addItem(BasketItem basketItem) {
		if (basketItems == null){
			basketItems = new ArrayList<>();
		}
		basketItems.add(basketItem);
	}

    /**
     * Remove item.
     *
     * @param basketItem the basket item
     */
    public void removeItem(BasketItem basketItem) {
		basketItems.remove(basketItem);
		basketItem.getProduct().getBasketItems().remove(basketItem);
	}
}
