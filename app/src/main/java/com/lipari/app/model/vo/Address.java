package com.lipari.app.model.vo;

public class Address {
	private int userId;
	private String indirizzo;
	public Address(int userId, String indirizzo) {
		super();
		this.userId = userId;
		this.indirizzo = indirizzo;
	}
	@Override
	public String toString() {
		return "Address [userId=" + userId + ", indirizzo=" + indirizzo + "]";
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

	

}
