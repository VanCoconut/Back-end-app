package com.lipari.app.orders.repositories;

import com.lipari.app.orders.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
   // List<Order> findAllOrderAppByUserId(Long userID);

    //boolean existsByOrderId(String orderID);
}
