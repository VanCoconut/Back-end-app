package com.lipari.app.model.vo;

public class Basket {
	private String orderId;
	private int productId, quantita;

	public Basket(String orderId, int productId, int quantita) {
		super();
		this.orderId = orderId;
		this.productId = productId;
		this.quantita = quantita;
	}

	@Override
	public String toString() {
		return "Basket [orderId=" + orderId + ", productId=" + productId + ", quantita=" + quantita + "]";
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

}
