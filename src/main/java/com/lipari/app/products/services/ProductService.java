package com.lipari.app.products.services;


import com.lipari.app.basket.entities.Basket;
import com.lipari.app.basket.repositories.BasketRepository;
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

@Service
public class ProductService {

    ProductRepository productRepository;
    BasketRepository basketRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, BasketRepository basketRepository){
        this.productRepository = productRepository;
        this.basketRepository = basketRepository;
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
    public Product getProductById(Long id) {
        Product product = productRepository.findById(id)
                          .orElseThrow(() -> new NotFoundException("Product not found id - "+id));
        return product;
    }
    @Transactional(rollbackFor = DataException.class,readOnly = true)
    public List<Product> getAllProducts() {
        List<Product> products;
        products = productRepository.findAll();
        return products;
    }
    @Transactional(rollbackFor = DataException.class)
    public String updateProductById(Product updatedProduct, Long id) {
            if (!productRepository.existsById(id)) {
                throw new NotFoundException("Product not found");
            }
            if (!isValidProduct(updatedProduct)) {
                throw new InvalidDataException("Invalid product data");
            }
            if (!productRepository.existsByCodice(updatedProduct.getCodice())) {
                throw new AlreadyExistsException("Product code already exists");
            }
            updatedProduct.setId(id);
            productRepository.save(updatedProduct);
            return "the product has been successfully updated:\n"+ updatedProduct;
    }
    @Transactional(rollbackFor = DataException.class)
    public String removeProductById(Long id) {
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
                product.getQuantity() > 0;
    }

    public int checkAvailableQuantity(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found"));
        return product.getQuantity();
    }

    @Transactional(rollbackFor = DataException.class)
    public void updateProductQuantity(Long productId, int newQuantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found id - " + productId));
        product.setQuantity(newQuantity);
        productRepository.save(product);
    }
}
