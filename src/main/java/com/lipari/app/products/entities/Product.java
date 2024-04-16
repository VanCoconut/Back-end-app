package com.lipari.app.products.entities;

import com.lipari.app.basket.entities.BasketItem;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="t_product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long id;

	@Column(name = "code", columnDefinition = "int")
	private int codice;

	@Column(name = "description", columnDefinition = "varchar(255)")
	private String descrizione;

	@Column(name = "cost", columnDefinition = "double(10, 2)")
	private double costo;

	@Column(name = "quantity", columnDefinition = "int")
	private int quantity;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<BasketItem> basketItems;
	public Product(){}

	public Product(int codice, String descrizione, double costo, int quantity, List<BasketItem> basketItems) {
		this.codice = codice;
		this.descrizione = descrizione;
		this.costo = costo;
		this.quantity = quantity;
		this.basketItems = basketItems;
	}

    @Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", codice=" + codice +
				", descrizione='" + descrizione + '\'' +
				", costo=" + costo +
				", quantity=" + quantity +
				", basketItems=" + basketItems +
				'}';
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public List<BasketItem> getBasketItems() {
		return basketItems;
	}

	public void setBasketItems(List<BasketItem> basketItems) {
		this.basketItems = basketItems;
	}
}
