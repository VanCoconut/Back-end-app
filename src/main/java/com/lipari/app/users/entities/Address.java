package com.lipari.app.users.entities;

public class Address {
	private int id,userId;
	private String indirizzo;
	
	public Address() {}

	public Address(int id, int userId, String indirizzo) {
		super();
		this.id = id;
		this.userId = userId;
		this.indirizzo = indirizzo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", userId=" + userId + ", indirizzo=" + indirizzo + "]";
	}
	

	

}
