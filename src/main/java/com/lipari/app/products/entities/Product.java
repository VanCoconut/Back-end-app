package com.lipari.app.products.entities;

import com.lipari.app.basket.entities.BasketItem;
import jakarta.persistence.*;

import java.util.List;

/**
 * The type Product.
 */
@Entity
@Table(name="t_product")
public class Product {
    /**
     * The Id.
     */
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long id;

    /**
     * The Codice.
     */
    @Column(name = "code", columnDefinition = "int")
	private int codice;

    /**
     * The Descrizione.
     */
    @Column(name = "description", columnDefinition = "varchar(255)")
	private String descrizione;

    /**
     * The Costo.
     */
    @Column(name = "cost", columnDefinition = "double(10, 2)")
	private double costo;

    /**
     * The Quantity.
     */
    @Column(name = "quantity", columnDefinition = "int")
	private int quantity;

    /**
     * The Basket items.
     */
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<BasketItem> basketItems;

    /**
     * Instantiates a new Product.
     */
    public Product(){}

    /**
     * Instantiates a new Product.
     *
     * @param codice      the codice
     * @param descrizione the descrizione
     * @param costo       the costo
     * @param quantity    the quantity
     * @param basketItems the basket items
     */
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
     * Gets codice.
     *
     * @return the codice
     */
    public int getCodice() {
		return codice;
	}

    /**
     * Sets codice.
     *
     * @param codice the codice
     */
    public void setCodice(int codice) {
		this.codice = codice;
	}

    /**
     * Gets descrizione.
     *
     * @return the descrizione
     */
    public String getDescrizione() {
		return descrizione;
	}

    /**
     * Sets descrizione.
     *
     * @param descrizione the descrizione
     */
    public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

    /**
     * Gets costo.
     *
     * @return the costo
     */
    public double getCosto() {
		return costo;
	}

    /**
     * Sets costo.
     *
     * @param costo the costo
     */
    public void setCosto(double costo) {
		this.costo = costo;
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
}
