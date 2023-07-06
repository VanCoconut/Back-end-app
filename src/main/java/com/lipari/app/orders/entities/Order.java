package com.lipari.app.orders.entities;

import com.lipari.app.basket.entities.Basket;
import com.lipari.app.users.entities.Address;
import com.lipari.app.users.entities.AppUser;
import com.lipari.app.utils.RandomId;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.UUID;

/**
 * The type Order.
 */
@Entity
@Table(name = "t_order")
public class Order {
    /**
     * The Id.
     */
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long id;
    /**
     * The User.
     */
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE})
	@JoinColumn(name = "user_id", unique = false)
	private AppUser user;
    /**
     * The Data.
     */
    @Column(name = "date")
	private LocalDate data;
    /**
     * The Indirizzo.
     */
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE})
	@JoinColumn(name = "address_id", unique = false)
	private Address indirizzo;
    /**
     * The Basket.
     */
    @OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "basket_id", unique = false)
	private Basket basket;

    /**
     * Instantiates a new Order.
     */
    public Order() {
	}

    /**
     * Instantiates a new Order.
     *
     * @param user      the user
     * @param data      the data
     * @param indirizzo the indirizzo
     * @param basket    the basket
     */
    public Order(AppUser user, LocalDate data, Address indirizzo, Basket basket) {
		this.user = user;
		this.data = data;
		this.indirizzo = indirizzo;
		this.basket = basket;
	}

	/*@PrePersist
	public void generateRandomId() {
		RandomId randomId = new RandomId();
		String string = randomId.generateVarchar(); // Genera una stringa casuale come ID
		this.id = string;
	}*/

	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", user=" + user +
				", data=" + data +
				", indirizzo=" + indirizzo +
				", basket=" + basket +
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
     * Gets user.
     *
     * @return the user
     */
    public AppUser getUser() {
		return user;
	}

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(AppUser user) {
		this.user = user;
	}

    /**
     * Gets data.
     *
     * @return the data
     */
    public LocalDate getData() {
		return data;
	}

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(LocalDate data) {
		this.data = data;
	}

    /**
     * Gets indirizzo.
     *
     * @return the indirizzo
     */
    public Address getIndirizzo() {
		return indirizzo;
	}

    /**
     * Sets indirizzo.
     *
     * @param indirizzo the indirizzo
     */
    public void setIndirizzo(Address indirizzo) {
		this.indirizzo = indirizzo;
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
}
