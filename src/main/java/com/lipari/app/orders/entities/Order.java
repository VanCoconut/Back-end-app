package com.lipari.app.orders.entities;

import com.lipari.app.basket.entities.Basket;
import com.lipari.app.users.entities.Address;
import com.lipari.app.users.entities.AppUser;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "t_order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long id;
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE})
	@JoinColumn(name = "user_id", unique = false)
	private AppUser appUser;
	@Column(name = "date")
	private LocalDate data;
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE})
	@JoinColumn(name = "address_id", unique = false)
	private Address indirizzo;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "basket_id", unique = false)
	private Basket basket;

	public Order() {
	}

	public Order(AppUser appUser, LocalDate data, Address indirizzo, Basket basket) {
		this.appUser = appUser;
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
				", user=" + appUser +
				", data=" + data +
				", indirizzo=" + indirizzo +
				", basket=" + basket +
				'}';
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AppUser getUser() {
		return appUser;
	}

	public void setUser(AppUser appUser) {
		this.appUser = appUser;
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
