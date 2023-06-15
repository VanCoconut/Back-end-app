package com.lipari.app.orders.repositories;

import com.lipari.app.orders.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findAllOrderByUserId(int userID);

    //boolean existsByOrderId(String orderID);
}
