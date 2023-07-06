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
import com.lipari.app.users.entities.AppUser;
import com.lipari.app.users.repositories.UserRepo;
import com.lipari.app.utils.RandomId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lipari.app.commons.exception.utils.DataException;
import com.lipari.app.users.repositories.AddressRepo;
import com.lipari.app.basket.entities.Basket;
import com.lipari.app.products.entities.Product;

/**
 * The type Order service.
 */
@Service
public class OrderService {

    /**
     * The Order repository.
     */
    private final OrderRepository orderRepository;
    /**
     * The User repository.
     */
    private final UserRepo userRepository;
    /**
     * The Product repository.
     */
    private final ProductRepository productRepository;
    /**
     * The Address repository.
     */
    private final AddressRepo addressRepository ;
    /**
     * The Basket repository.
     */
    private final BasketRepository basketRepository;
    /**
     * The Random id.
     */
    private final RandomId randomId;

    /**
     * Instantiates a new Order service.
     *
     * @param orderRepository   the order repository
     * @param userRepository    the user repository
     * @param productRepository the product repository
     * @param addressRepository the address repository
     * @param basketRepository  the basket repository
     * @param randomId          the random id
     */
    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepo userRepository, ProductRepository productRepository, AddressRepo addressRepository, BasketRepository basketRepository, RandomId randomId) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.addressRepository = addressRepository;
        this.basketRepository = basketRepository;
        this.randomId = randomId;
    }


    /**
     * Retrieve all orders list.
     *
     * @param userId the user id
     * @return the list
     */
    public List<Order> retrieveAllOrders(Long userId) {
        List<Order> orders = orderRepository.findAllOrderByUserId(userId);
        if (!userRepository.existsById(userId)){
            throw new NotFoundException("User not found");
        }
        if (!(userId > 0)){
            throw new InvalidDataException("Invalid user data");
        }
        return orders;
    }


    /*public Map<Integer, String> retrieveBasketXOrder(String orderId) {
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

    }*/


    /**
     * Find order by id optional.
     *
     * @param id the id
     * @return the optional
     */
    public Optional<Order> findOrderById(Long id){
        if (!orderRepository.existsById(id)){
            throw new NotFoundException("Order not found");
        }
            return orderRepository.findById(id);
    }

    /**
     * Add order string.
     *
     * @param order the order
     * @return the string
     */
    public String addOrder(Order order) {
       // order.setId(randomId.generateVarchar());
        //order.setBasket(order.getUser().getBasket());

		 //if (!isValidOrder(order)){ throw new InvalidDataException("Invalid order data"); }
         //if (orderRepository.existsById(order.getId())){ throw new AlreadyExistsException("Order already exists"); }

        //2 problemi Id che da problemi e viene creato un'altro user con la chiamata
        orderRepository.save(order);
        return "Order created";

    }

    /**
     * Delete order string.
     *
     * @param orderId the order id
     * @return the string
     */
    public String deleteOrder(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new NotFoundException("Order not found");
        }
        orderRepository.deleteById(orderId);
        return "Deletion done";
    }

    /**
     * Update string.
     *
     * @param updateOrder the update order
     * @param id          the id
     * @return the string
     */
    public String update(Order updateOrder, Long id){
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


    /**
     * Is valid order boolean.
     *
     * @param order the order
     * @return the boolean
     */
    private boolean isValidOrder(Order order) {
        return
                order.getUser() != null &&
                order.getData() != null &&
                order.getIndirizzo() != null;
    }

}