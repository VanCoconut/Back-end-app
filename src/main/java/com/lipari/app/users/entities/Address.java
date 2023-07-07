package com.lipari.app.users.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * The type Address.
 */
@Entity
@Table(name="t_address")
public class Address {

    /**
     * The Id.
     */
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="address_id")
	private Long id;

    /**
     * The Indirizzo.
     */
    @Column(name="address")
	private String indirizzo;

    /**
     * Instantiates a new Address.
     */
    public Address() {}

    /**
     * Instantiates a new Address.
     *
     * @param indirizzo the indirizzo
     */
    public Address(String indirizzo) {
		this.indirizzo = indirizzo;
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
     * Gets indirizzo.
     *
     * @return the indirizzo
     */
    public String getIndirizzo() {
		return indirizzo;
	}

    /**
     * Sets indirizzo.
     *
     * @param indirizzo the indirizzo
     */
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
