package com.lipari.app.basket.entities;

import com.lipari.app.products.entities.Product;
import com.lipari.app.users.entities.Address;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="t_basket")
public class Basket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToMany(mappedBy = "basket", cascade = CascadeType.ALL)
	private List<BasketItem> basketItems;

	public Basket() {
	}

	public Basket(Long id, List<BasketItem> basketItems) {
		this.id = id;
		this.basketItems = basketItems;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<BasketItem> getBasketItems() {
		return basketItems;
	}

	public void setBasketItems(List<BasketItem> basketItems) {
		this.basketItems = basketItems;
	}

}
