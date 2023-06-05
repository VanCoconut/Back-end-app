package com.lipari.app.model.vo;

public class Product {
	private int id, codice;
	private String descrizione;
	private float costo;
	private int magazzino;

	public Product(int id, int codice, String descrizione, float costo, int magazzino) {
		super();
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.costo = costo;
		this.magazzino = magazzino;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", codice=" + codice + ", descrizione=" + descrizione + ", costo=" + costo
				+ ", magazzino=" + magazzino + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public int getMagazzino() {
		return magazzino;
	}

	public void setMagazzino(int magazzino) {
		this.magazzino = magazzino;
	}

}
