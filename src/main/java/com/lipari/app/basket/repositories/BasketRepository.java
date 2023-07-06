package com.lipari.app.basket.repositories;

import com.lipari.app.basket.entities.Basket;
import com.lipari.app.products.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Basket repository.
 */
@Repository
public interface BasketRepository extends JpaRepository<Basket,Long> {

}
