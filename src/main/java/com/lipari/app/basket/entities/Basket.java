package com.lipari.app.basket.entities;

import com.lipari.app.products.entities.Product;
import com.lipari.app.users.entities.Address;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="t_basket")
public class Basket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH})
	//@JoinColumn(name = "basket_id")
	private List<Product> productList;

	private int qta;

	public Basket() {
	}

	public Basket(List<Product> productList, int qta) {
		this.productList = productList;
		this.qta = qta;
	}

	@Override
	public String toString() {
		return "Basket{" +
				"id=" + id +
				", productList=" + productList +
				", qta=" + qta +
				'}';
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public int getQta() {
		return qta;
	}

	public void setQta(int qta) {
		this.qta = qta;
	}

	public void addProduct(Product product){
		if (productList.size() == 0){
			productList = new ArrayList<>();
		}
		productList.add(product);
	}
}
