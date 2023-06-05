package com.lipari.app.model.vo;

import java.time.LocalDate;

public class Order {
	private String id;
	private int userId;
	private LocalDate data;
	private String indirizzo;

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
