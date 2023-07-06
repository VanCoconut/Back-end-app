package com.lipari.app.users.entities;

import javax.persistence.*;

@Entity
@Table(name="t_address")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="address_id")
	private Long id;
	
	@Column(name="address")
	private String indirizzo;
	
	public Address() {}

	public Address(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	@Override
	public String toString() {
		return "Address{" +
				"id=" + id +
				", indirizzo='" + indirizzo + '\'' +
				'}';
	}
}
