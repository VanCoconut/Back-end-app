package com.lipari.app.products.services;


import com.lipari.app.commons.exception.utils.AlreadyExistsException;
import com.lipari.app.commons.exception.utils.DataException;
import com.lipari.app.commons.exception.utils.InvalidDataException;
import com.lipari.app.commons.exception.utils.NotFoundException;
import com.lipari.app.products.entities.Product;
import com.lipari.app.products.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Transactional(rollbackFor = DataException.class)
    public String createProduct(Product product){
            if (!isValidProduct(product)) {
                throw new InvalidDataException("Invalid product data");
            }
            if (productRepository.existsByCodice(product.getCodice())) {
                throw new AlreadyExistsException("Codice already exists in the database");
            }
        productRepository.save(product);
        return "Product created";
    }
    @Transactional(rollbackFor = DataException.class, readOnly = true)
    public Product getProductById(Integer id) {
        Optional<Product> result = productRepository.findById(id);
        Product product = null;
        if(result.isPresent()){
            product = result.get();
        }else {
            throw new NotFoundException("Product not found id - "+id);
        }

        return product;
    }
    @Transactional(rollbackFor = DataException.class,readOnly = true)
    public List<Product> getAllProducts() {
        List<Product> products;
        products = productRepository.findAll();
        return products;
    }
    @Transactional(rollbackFor = DataException.class)
    public String updateProductById(Product updatedProduct, Integer id) {
            if (!productRepository.existsById(id)) {
                throw new NotFoundException("Product not found");
            }
            if (!isValidProduct(updatedProduct)) {
                throw new InvalidDataException("Invalid product data");
            }
            if (!productRepository.existsByCodice(updatedProduct.getCodice())) {
                throw new AlreadyExistsException("Product code already exists");
            }
            Product existingProduct = productRepository.getProductById(id);

            existingProduct.setCodice(updatedProduct.getCodice());
            existingProduct.setDescrizione(updatedProduct.getDescrizione());
            existingProduct.setCosto(updatedProduct.getCosto());
            existingProduct.setMagazzino(updatedProduct.getMagazzino());
            productRepository.save(existingProduct);
            return "the product has been successfully updated:\n"+ existingProduct;
    }
    @Transactional(rollbackFor = DataException.class)
    public String removeProductById(Integer id) {
        if(productRepository.existsById(id)){
            Product existingProduct = productRepository.getProductById(id);
            productRepository.delete(existingProduct);
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
