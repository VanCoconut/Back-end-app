package com.lipari.app.orders.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name = "t_order")
public class Order {
	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "userid")
	private int userId;
	@Column(name = "data")
	private LocalDate data;
	@Column(name = "indirizzo")
	private String indirizzo;

	public Order() {
	}

	public Order(String id, int userId, LocalDate data, String indirizzo) {
		super();
		this.id = id;
		this.userId = userId;
		this.data = data;
		this.indirizzo = indirizzo;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", data=" + data + ", indirizzo=" + indirizzo + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

}
