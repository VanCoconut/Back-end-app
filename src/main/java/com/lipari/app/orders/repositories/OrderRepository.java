package com.lipari.app.orders.repositories;

import com.lipari.app.orders.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findAllOrderByUserId(Long userID);

    //boolean existsByOrderId(String orderID);
}
