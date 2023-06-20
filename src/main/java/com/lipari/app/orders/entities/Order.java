package com.lipari.app.orders.entities;

import com.lipari.app.basket.entities.Basket;
import com.lipari.app.users.entities.Address;
import com.lipari.app.users.entities.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "t_order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "order_id")
	private UUID id;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE})
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "date")
	private LocalDate data;
	
	@OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE})
	@JoinColumn(name = "address_id")
	private Address indirizzo;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "basket_id")
	private Basket basket;

	public Order() {
	}

	public Order(User user, LocalDate data, Address indirizzo, Basket basket) {
		this.user = user;
		this.data = data;
		this.indirizzo = indirizzo;
		this.basket = basket;
	}

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

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Address getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(Address indirizzo) {
		this.indirizzo = indirizzo;
	}

	public Basket getBasket() {
		return basket;
	}

	public void setBasket(Basket basket) {
		this.basket = basket;
	}
}
