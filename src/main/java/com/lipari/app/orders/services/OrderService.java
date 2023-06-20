package com.lipari.app.orders.services;

import java.util.*;
import java.util.stream.Collectors;

import com.lipari.app.basket.repositories.BasketRepository;
import com.lipari.app.commons.exception.utils.AlreadyExistsException;
import com.lipari.app.commons.exception.utils.InvalidDataException;
import com.lipari.app.commons.exception.utils.NotFoundException;
import com.lipari.app.orders.entities.Order;
import com.lipari.app.orders.repositories.OrderRepository;
import com.lipari.app.products.repositories.ProductRepository;
import com.lipari.app.users.entities.User;
import com.lipari.app.users.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipari.app.commons.exception.utils.DataException;
import com.lipari.app.users.repositories.AddressRepo;
import com.lipari.app.basket.entities.Basket;
import com.lipari.app.products.entities.Product;

@Service
public class OrderService {

	private final OrderRepository orderRepository;
	private final UserRepo userRepository;
	private final ProductRepository productRepository;
	private final AddressRepo addressRepository;
	private final BasketRepository basketRepository;

	@Autowired
	public OrderService(OrderRepository orderRepository, UserRepo userRepository, ProductRepository productRepository,
			AddressRepo addressRepository, BasketRepository basketRepository) {
		this.orderRepository = orderRepository;
		this.userRepository = userRepository;
		this.productRepository = productRepository;
		this.addressRepository = addressRepository;
		this.basketRepository = basketRepository;
	}

	public List<Order> retrieveAllOrders(Long userId) {
		List<Order> orders = orderRepository.findAllOrderByUserId(userId);
		if (!userRepository.existsById(userId)) {
			throw new NotFoundException("User not found");
		}
		if (!(userId > 0)) {
			throw new InvalidDataException("Invalid user data");
		}
		return orders;
	}

	/*
	 * public Map<Integer, String> retrieveBasketXOrder(String orderId) { if
	 * (!orderRepository.existsById(orderId)){ throw new
	 * NotFoundException("Order not found"); } Map<Integer, String> m = new
	 * HashMap<>(); Map<Integer, Integer> m1 = new HashMap<>(); try { m1 =
	 * basketDao.getBasket(orderId).stream().collect(Collectors.toMap(Basket::
	 * getQuantita, Basket::getProductId)); Iterator<Map.Entry<Integer, Integer>>
	 * iterator = m1.entrySet().iterator(); while (iterator.hasNext()) {
	 * Map.Entry<Integer, Integer> entry = iterator.next(); m.put(entry.getKey(),
	 * productDao.getProduct(entry.getValue()).getDescrizione()); } return m; }
	 * catch (DataException e) { e.printStackTrace(); } return null;
	 * 
	 * }
	 */

	public String addProduct(Long userId, Long productId, int qta) {

		if (userId == null || productId == null || qta < 0) {
			throw new InvalidDataException("");
		}

		Basket basket;
		User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(""));
		Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException(""));
		if (user.getBasket() == null) {
			basket = new Basket();
		} else {
			basket = user.getBasket();
		}
		basket.addProduct(product);
		basket.setQta(qta);
		user.setBasket(basket);
		userRepository.save(user);
		return "Product added";
	}

	public Optional<Order> findOrderById(UUID id) {
		if (!orderRepository.existsById(id)) {
			throw new NotFoundException("Order not found");
		}
		return orderRepository.findById(id);
	}

	public String addOrder(Order order) {
		/*
		 * if (!isValidOrder(order)){ throw new
		 * InvalidDataException("Invalid order data"); } if
		 * (orderRepository.existsById(order.getId())){ throw new
		 * AlreadyExistsException("Order already exists"); }
		 */

		orderRepository.save(order);
		return "Order created";

	}

	public String deleteOrder(UUID orderId) {
		if (!orderRepository.existsById(orderId)) {
			throw new NotFoundException("Order not found");
		}
		orderRepository.deleteById(orderId);
		return "Deletion done";
	}

	public String update(Order updateOrder, UUID id) {
		if (!orderRepository.existsById(id)) {
			throw new NotFoundException("Order not found");
		}
		if (!isValidOrder(updateOrder)) {
			throw new InvalidDataException("Invalid order data");
		}

		Optional<Order> existingOrder = orderRepository.findById(id);
		existingOrder.get().setId(updateOrder.getId());
		existingOrder.get().setData(updateOrder.getData());
		existingOrder.get().setIndirizzo(updateOrder.getIndirizzo());
		orderRepository.save(existingOrder.get());
		return "The order has been updated";
	}

	private boolean isValidOrder(Order order) {
		return order.getId() != null && order.getUser() != null && order.getData() != null
				&& order.getIndirizzo() != null;
	}

}