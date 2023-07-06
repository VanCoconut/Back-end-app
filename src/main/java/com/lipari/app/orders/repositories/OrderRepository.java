package com.lipari.app.orders.repositories;

import com.lipari.app.orders.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * The interface Order repository.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    /**
     * Find all order by user id list.
     *
     * @param userID the user id
     * @return the list
     */
    List<Order> findAllOrderByUserId(Long userID);

    //boolean existsByOrderId(String orderID);
}
