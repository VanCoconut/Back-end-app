package com.lipari.app.controllers;

import com.lipari.app.exception.DataErrorResponse;
import com.lipari.app.exception.DataException;
import com.lipari.app.model.vo.Product;
import com.lipari.app.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products/")
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @PostMapping()
    public ResponseEntity<String> insertProduct(@RequestBody Product product) throws DataException {
        return productService.createProduct(product);
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() throws DataException {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductbyId(@PathVariable int id) throws DataException {
        return productService.getProductbyId(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateProductById(@RequestBody Product product, @PathVariable int id) throws DataException {
        return productService.updateProductById(product, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeProductById(@PathVariable int id) throws DataException {
        return productService.removeProductById(id);
    }

    @ExceptionHandler(DataException.class)
    public ResponseEntity<DataErrorResponse> handlerException(DataException ex) {
        return productService.handlerException(ex);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DataErrorResponse> handleGenericException(Exception ex) {
        return productService.handleGenericException(ex);
    }
}
