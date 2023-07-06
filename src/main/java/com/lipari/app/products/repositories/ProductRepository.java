package com.lipari.app.products.repositories;

import com.lipari.app.products.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Product repository.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    boolean existsById(Long id);

    /**
     * Exists by codice boolean.
     *
     * @param codice the codice
     * @return the boolean
     */
    boolean existsByCodice(int codice);

    /**
     * Gets product by id.
     *
     * @param id the id
     * @return the product by id
     */
    Product getProductById(Long id);

}
