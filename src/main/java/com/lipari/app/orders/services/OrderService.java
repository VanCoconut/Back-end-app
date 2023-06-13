package com.lipari.app.orders.services;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.lipari.app.orders.entities.Order;
import com.lipari.app.orders.repositories.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipari.app.commons.exception.utils.DataException;
import com.lipari.app.users.repositories.AddressDao;
import com.lipari.app.basket.repositories.BasketDao;
import com.lipari.app.products.repositories.ProductDao;
import com.lipari.app.users.repositories.UserDao;
import com.lipari.app.basket.entities.Basket;
import com.lipari.app.products.entities.Product;

@Service
public class OrderService {

	private final OrderDao orderDao ;
	private final UserDao userDao;
	private final ProductDao productDao ;
	private final AddressDao addressDao ;
	private final BasketDao basketDao ;

	@Autowired
	public OrderService(OrderDao orderDao, UserDao userDao, ProductDao productDao, AddressDao addressDao, BasketDao basketDao) {
		this.orderDao = orderDao;
		this.userDao = userDao;
		this.productDao = productDao;
		this.addressDao = addressDao;
		this.basketDao = basketDao;
	}

	public List<Order> retrieveAllOrders(int userId) {

		try {
			return orderDao.getAllOrders(userId);
		} catch (DataException e) {
			e.printStackTrace();
		}
		return null;

	}

	public List<Product> retrieveAllProduct() {

		try {
			return productDao.getAllProduct();
		} catch (DataException e) {
			e.printStackTrace();
		}
		return null;

	}

	public Map<Integer, String> retrieveBasketXOrder(String orderId) {
		Map<Integer, String> m = new HashMap<>();
		Map<Integer, Integer> m1 = new HashMap<>();
		try {
			m1 = basketDao.getBasket(orderId).stream().collect(Collectors.toMap(Basket::getQuantita, Basket::getProductId));
			Iterator<Map.Entry<Integer, Integer>> iterator = m1.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<Integer, Integer> entry = iterator.next();
				m.put(entry.getKey(), productDao.getProduct(entry.getValue()).getDescrizione());
			}
			return m;
		} catch (DataException e) {
			e.printStackTrace();
		}
		return null;

	}

	public boolean addProduct(String orderId, int productId, int qta) {

		try {
			return basketDao.setBasket(orderId, productId, qta);
		} catch (DataException e) {
			e.printStackTrace();
		}
		return false;

	}

	public boolean addOrder(String orderId, int userId, LocalDate data, String indirizzo) {

		try {
			return orderDao.setOrder(orderId, userId, data, indirizzo);
		} catch (DataException e) {
			e.printStackTrace();
		}
		return false;

	}

	public boolean deleteOrder(String orderId) {

		try {
			return orderDao.deleteOrder(orderId);
		} catch (DataException e) {
			e.printStackTrace();
		}
		return false;

	}

	public Product findProduct(int productId) {

		try {
			return productDao.getProduct(productId);
		} catch (DataException e) {
			e.printStackTrace();
		}
		return null;

	}

	public boolean findAddress(int userId, String indirizzo) {

		try {
			return addressDao.getAllAddress(userId).stream().filter(e -> e.equalsIgnoreCase(indirizzo)).toList().isEmpty();
		} catch (DataException e) {
			e.printStackTrace();
		}

		return false;
	}
}
