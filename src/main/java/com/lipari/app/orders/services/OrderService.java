package com.lipari.app.orders.services;


import java.util.*;
import java.util.stream.Collectors;

import com.lipari.app.commons.exception.utils.AlreadyExistsException;
import com.lipari.app.commons.exception.utils.InvalidDataException;
import com.lipari.app.commons.exception.utils.NotFoundException;
import com.lipari.app.orders.entities.Order;
import com.lipari.app.orders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lipari.app.commons.exception.utils.DataException;
import com.lipari.app.users.repositories.AddressDao;
import com.lipari.app.basket.repositories.BasketDao;
import com.lipari.app.products.repositories.ProductDao;
import com.lipari.app.users.repositories.UserDao;
import com.lipari.app.basket.entities.Basket;
import com.lipari.app.products.entities.Product;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserDao userDao;
    private final ProductDao productDao ;
    private final AddressDao addressDao ;
    private final BasketDao basketDao ;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserDao userDao, ProductDao productDao, AddressDao addressDao, BasketDao basketDao) {
        this.orderRepository = orderRepository;
        this.userDao = userDao;
        this.productDao = productDao;
        this.addressDao = addressDao;
        this.basketDao = basketDao;
    }

    public List<Order> retrieveAllOrders(Integer userId) {
        List<Order> orders = orderRepository.findAllOrderByUserId(userId);
        if (!userDao.existsById(userId)){
            throw new NotFoundException("User not found");
        }
        if (!(userId > 0)){
            throw new InvalidDataException("Invalid user data");
        }
        return orders;
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
        if (!orderRepository.existsById(orderId)){
            throw new NotFoundException("Order not found");
        }
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

    public String addProduct(String orderId, int productId, int qta) {
        if (orderRepository.existsById(orderId)){
            throw new AlreadyExistsException("Order already exists");
        }
        if (!productDao.existsById(productId)){
            throw new NotFoundException("Product not found");
        }
        if (!(qta > 0)){
            throw new InvalidDataException("Invalid product data");
        }
        basketDao.setBasket(orderId, productId, qta);
        return "Product added";
    }

    public Optional<Order> findOrderById(String id){
        if (!orderRepository.existsById(id)){
            throw new NotFoundException("Order not found");
        }
            return orderRepository.findById(id);
    }
    public String addOrder(Order order) {
        if (!isValidOrder(order)){
            throw new InvalidDataException("Invalid order data");
        }
        if (orderRepository.existsById(order.getId())){
            throw new AlreadyExistsException("Order already exists");
        }

        orderRepository.save(order);
        return "Order created";

    }

    public String deleteOrder(String orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new NotFoundException("Order not found");
        }
        orderRepository.deleteById(orderId);
        return "Deletion done";
    }

    public Product findProduct(int productId) {
        if (!productDao.existsById(productId)){
            throw new NotFoundException("Product not found");
        }
        return productDao.getProduct(productId);

    }

    public boolean findAddress(int userId, String indirizzo) {
        if (!userDao.existsById(userId)){
            throw new NotFoundException("User not found");
        }
        //return addressDao.getAllAddress(userId).stream().filter(e -> e.equalsIgnoreCase(indirizzo)).toList().isEmpty();
        return false;

    }
    public String update(Order updateOrder, String id){
        if (!orderRepository.existsById(id)){
            throw new NotFoundException("Order not found");
        }
        if (!isValidOrder(updateOrder)){
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
        return order.getId() != null &&
                order.getUserId() > 0 &&
                order.getData() != null &&
                order.getIndirizzo() != null;
    }

}