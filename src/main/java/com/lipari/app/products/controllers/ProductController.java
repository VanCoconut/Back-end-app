package com.lipari.app.products.controllers;

import com.lipari.app.commons.exception.utils.DataException;
import com.lipari.app.products.services.ProductService;
import com.lipari.app.products.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Product controller.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    /**
     * The Product service.
     */
    ProductService productService;

    /**
     * Instantiates a new Product controller.
     *
     * @param productService the product service
     */
    @Autowired
    public ProductController(ProductService productService){
        this.productService=productService;
    }

    /**
     * Insert product response entity.
     *
     * @param product the product
     * @return the response entity
     */
    @PostMapping()
    public ResponseEntity<String> insertProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }

    /**
     * Gets all products.
     *
     * @return the all products
     */
    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    /**
     * Get productby id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductbyId(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    /**
     * Update product by id response entity.
     *
     * @param product the product
     * @param id      the id
     * @return the response entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProductById(@RequestBody Product product, @PathVariable Long id) {
        return ResponseEntity.ok(productService.updateProductById(product, id));
    }

    /**
     * Remove product by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.removeProductById(id));
    }

}
