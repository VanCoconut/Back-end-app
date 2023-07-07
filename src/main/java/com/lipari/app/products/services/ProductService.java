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

/**
 * The type Product service.
 */
@Service
public class ProductService {

    /**
     * The Product repository.
     */
    ProductRepository productRepository;
    /**
     * The Basket repository.
     */
    BasketRepository basketRepository;

    /**
     * Instantiates a new Product service.
     *
     * @param productRepository the product repository
     * @param basketRepository  the basket repository
     */
    @Autowired
    public ProductService(ProductRepository productRepository, BasketRepository basketRepository){
        this.productRepository = productRepository;
        this.basketRepository = basketRepository;
    }

    /**
     * Create product string.
     *
     * @param product the product
     * @return the string
     */
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

    /**
     * Gets product by id.
     *
     * @param id the id
     * @return the product by id
     */
    @Transactional(rollbackFor = DataException.class, readOnly = true)
    public Product getProductById(Long id) {
        Product product = productRepository.findById(id)
                          .orElseThrow(() -> new NotFoundException("Product not found id - "+id));
        return product;
    }

    /**
     * Gets all products.
     *
     * @return the all products
     */
    @Transactional(rollbackFor = DataException.class,readOnly = true)
    public List<Product> getAllProducts() {
        List<Product> products;
        products = productRepository.findAll();
        return products;
    }

    /**
     * Update product by id string.
     *
     * @param updatedProduct the updated product
     * @param id             the id
     * @return the string
     */
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

    /**
     * Remove product by id string.
     *
     * @param id the id
     * @return the string
     */
    @Transactional(rollbackFor = DataException.class)
    public String removeProductById(Long id) {
        if(productRepository.existsById(id)){
            Product existingProduct = productRepository.getProductById(id);
            List<Basket> baskets = basketRepository.findAll();
            for (Basket basket : baskets) {
                //basket.getProductList().remove(existingProduct);
            }
            productRepository.delete(existingProduct);
            return "Deletion done";
        }else {
            throw new NotFoundException("Product not found");
        }
    }

    /**
     * Is valid product boolean.
     *
     * @param product the product
     * @return the boolean
     */
    private boolean isValidProduct(Product product) {
        return product.getCodice() > 0 &&
                product.getDescrizione() != null &&
                !product.getDescrizione().isEmpty() &&
                product.getCosto() > 0.0f &&
                product.getQuantity() > 0;
    }

    /**
     * Check available quantity int.
     *
     * @param productId the product id
     * @return the int
     */
    public int checkAvailableQuantity(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found"));
        return product.getQuantity();
    }

    /**
     * Update product quantity.
     *
     * @param productId   the product id
     * @param newQuantity the new quantity
     */
    @Transactional(rollbackFor = DataException.class)
    public void updateProductQuantity(Long productId, int newQuantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found id - " + productId));
        product.setQuantity(newQuantity);
        productRepository.save(product);
    }
}
