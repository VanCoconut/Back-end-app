package com.lipari.app.users.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="t_role")
public class Role {
	
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "descrizione")
	private String descrizione;
	
	public Role() {}

	public Role(int id, String descrizione) {
		super();
		this.id = id;
		this.descrizione = descrizione;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", descrizione=" + descrizione + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
