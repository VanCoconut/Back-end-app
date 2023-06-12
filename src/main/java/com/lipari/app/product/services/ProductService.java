package com.lipari.app.product.services;


import com.lipari.app.commons.exception.entities.DataErrorResponse;
import com.lipari.app.commons.exception.utils.DataException;
import com.lipari.app.product.repositories.ProductDao;
import com.lipari.app.product.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ProductService {

    ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao){
        this.productDao = productDao;
    }

    public ResponseEntity<String> createProduct(Product product){
        try {
            productDao.setProduct(product.getCodice(), product.getDescrizione(), product.getCosto(), product.getMagazzino());
        }catch (DataException e){
            throw new DataException("missing data");
        }catch (SQLException e) {
            throw new DataException(e);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Product created");
    }


    public ResponseEntity<Product> getProductbyId(int id) {
        Product product;
        try {
            product = productDao.getProduct(id);
        }catch (DataException e){
            throw new DataException(e);
        }
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products;
        try {
            products = productDao.getAllProduct();
        } catch (DataException e) {
            throw new DataException(e);
        }
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    public ResponseEntity<String> updateProductById(Product updatedProduct, int id) {
        try {
            if (productDao.existsById(id)) {
                Product existingProduct = productDao.getProduct(id);
                if (updatedProduct.getCodice() != 0) {
                    existingProduct.setCodice(updatedProduct.getCodice());
                }
                if (updatedProduct.getDescrizione() != null) {
                    existingProduct.setDescrizione(updatedProduct.getDescrizione());
                }
                if (updatedProduct.getCosto() != 0.0f) {
                    existingProduct.setCosto(updatedProduct.getCosto());
                }
                if (updatedProduct.getMagazzino() != 0) {
                    existingProduct.setMagazzino(updatedProduct.getMagazzino());
                }
               productDao.updateProduct(existingProduct);
               return ResponseEntity.status(HttpStatus.OK).body("the product has been successfully updated:\n"+ productDao.getProduct(id));
            } else {
                throw new DataException("Product not found");
            }
        }catch(DataException e){
            throw new DataException(e);
        }
    }

    public ResponseEntity<String> removeProductById(int id) {
        try {
        if(productDao.existsById(id)){
            productDao.deleteProduct(id);
            return ResponseEntity.ok("Deletion done");
        }else {
            throw new DataException("Product not found");
        }

        }catch(DataException e){
            throw new DataException(e);
        }
    }

    public ResponseEntity<DataErrorResponse> handlerException(DataException ex) {
        DataErrorResponse error = new DataErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<DataErrorResponse> handleGenericException(Exception ex) {
        DataErrorResponse error = new DataErrorResponse();

        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
