package com.lipari.app.users.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="t_role")
public class Role {
	
	@Id
	@Column(name = "role_id")
	private Long id;
	
	@Column(name = "description")
	private String descrizione;
	
	@OneToMany(mappedBy = "role", orphanRemoval = true, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    private List<User> appointments;
	
	public Role() {}

	public Role(Long id,String descrizione) {
		this.id=id;
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
