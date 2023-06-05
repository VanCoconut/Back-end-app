package com.lipari.app.model.vo;

public class Role {
	private int id;
	private String descrizione;

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
