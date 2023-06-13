package com.lipari.app.products.services;


import com.lipari.app.commons.exception.utils.AlreadyExistsException;
import com.lipari.app.commons.exception.utils.InvalidDataException;
import com.lipari.app.commons.exception.utils.NotFoundException;
import com.lipari.app.products.repositories.ProductDao;
import com.lipari.app.products.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao){
        this.productDao = productDao;
    }

    public String createProduct(Product product){
            if (!isValidProduct(product)) {
                throw new InvalidDataException("Invalid product data");
            }
            if (productDao.existsByCodice(product.getCodice())) {
                throw new AlreadyExistsException("Codice already exists in the database");
            }
            productDao.setProduct(product.getCodice(), product.getDescrizione(), product.getCosto(), product.getMagazzino());
        return "Product created";
    }

    public Product getProductById(int id) {
        return productDao.getProduct(id);
    }
    public List<Product> getAllProducts() {
        List<Product> products;
        products = productDao.getAllProduct();
        return products;
    }

    public String updateProductById(Product updatedProduct, int id) {
            if (!productDao.existsById(id)) {
                throw new NotFoundException("Product not found");
            }
            if (!isValidProduct(updatedProduct)) {
                throw new InvalidDataException("Invalid product data");
            }
            if (!productDao.existsByCodice(updatedProduct.getCodice())) {
                throw new AlreadyExistsException("Product code already exists");
            }
            Product existingProduct = productDao.getProduct(id);
            existingProduct.setCodice(updatedProduct.getCodice());
            existingProduct.setDescrizione(updatedProduct.getDescrizione());
            existingProduct.setCosto(updatedProduct.getCosto());
            existingProduct.setMagazzino(updatedProduct.getMagazzino());
            productDao.updateProduct(existingProduct);
            return "the product has been successfully updated:\n"+ productDao.getProduct(id);
    }

    public String removeProductById(int id) {
        if(productDao.existsById(id)){
            productDao.deleteProduct(id);
            return "Deletion done";
        }else {
            throw new NotFoundException("Product not found");
        }
    }

    private boolean isValidProduct(Product product) {
        return product.getCodice() > 0 &&
                product.getDescrizione() != null &&
                !product.getDescrizione().isEmpty() &&
                product.getCosto() > 0.0f &&
                product.getMagazzino() >= 0;
    }
}
