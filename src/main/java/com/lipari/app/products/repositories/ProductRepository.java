package com.lipari.app.products.repositories;

import com.lipari.app.products.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    boolean existsById(Integer id);
    boolean existsByCodice(int codice);

    Product getProductById(Integer id);

}
