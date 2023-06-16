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
	@Column(name = "role_id")
	private Long id;
	
	@Column(name = "description")
	private String descrizione;
	
	public Role() {}

	public Role(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", descrizione=" + descrizione + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
