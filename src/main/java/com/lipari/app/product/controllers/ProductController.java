package com.lipari.app.product.controllers;

import com.lipari.app.commons.exception.utils.DataException;
import com.lipari.app.product.services.ProductService;
import com.lipari.app.product.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @PostMapping()
    public ResponseEntity<String> insertProduct(@RequestBody Product product)  {
        return ResponseEntity.ok(productService.createProduct(product));
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() throws DataException {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductbyId(@PathVariable int id) throws DataException {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProductById(@RequestBody Product product, @PathVariable int id) throws DataException {
        return ResponseEntity.ok(productService.updateProductById(product, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeProductById(@PathVariable int id) throws DataException {
        return ResponseEntity.ok(productService.removeProductById(id));
    }

}
