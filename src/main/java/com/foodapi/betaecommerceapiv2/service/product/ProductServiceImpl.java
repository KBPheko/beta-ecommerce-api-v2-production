package com.foodapi.betaecommerceapiv2.service.product;

import com.foodapi.betaecommerceapiv2.exceptions.product.InvalidFilterException;
import com.foodapi.betaecommerceapiv2.exceptions.product.ProductExistsException;
import com.foodapi.betaecommerceapiv2.exceptions.product.ProductNotFoundException;
import com.foodapi.betaecommerceapiv2.models.product.Category;
import com.foodapi.betaecommerceapiv2.models.product.Product;
import com.foodapi.betaecommerceapiv2.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
//@Transactional
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;

    // Inject product repository
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /** Returns a list of products*/
    @Override
    public List<Product> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        if(allProducts.isEmpty()){
            return allProducts;
        }
        return allProducts;
    }

    /** Returns one product by product id*/
    @Override
    public Product getProductById(Long productId) throws ProductNotFoundException {
        return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product Not Found!"));
    }

    /** adds new product*/
    @Override
    @Transactional(rollbackOn = ProductExistsException.class) //wont add to db if exception is being thrown
    public Product createProduct(Product product) throws ProductExistsException {
        List<Product> products = getAllProducts();
        for (Product product1: products) {
            if (product1.getProductName() == product.getProductName()) {
                throw new ProductExistsException("Product already exists");
            }
        }
        return productRepository.save(product);
    }

    /** updates existing product*/
    @Override
    public Product updateExistingProduct(Long productId, Product product) throws ProductNotFoundException {

        Product existingProduct = getProductById(productId);
        Category category = product.getCategory();

        if (existingProduct == null){
            throw new ProductNotFoundException("Product Not Found!");
        }
        existingProduct.setProductName(product.getProductName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        Category existingCategory = existingProduct.getCategory();
        existingCategory.setCategoryName(category.getCategoryName());
        existingProduct.setCategory(existingCategory);
        existingProduct.setImageUrl(product.getImageUrl());
        // Get the current date and time
        Date dateUpdated = new Date();
        // Update the dateUpdated field
        existingProduct.setDateUpdated(dateUpdated);
        Date updatedAt = new Date();
        category.setDateUpdated(updatedAt);

        return productRepository.save(existingProduct);
    }

    /** deletes existing product*/
    @Transactional(rollbackOn = ProductNotFoundException.class)
    public void deleteProduct(Long productId) throws ProductNotFoundException {
        Product prodItem = productRepository.findById(productId).orElseThrow(() ->
                new ProductNotFoundException("Product not found"));
        productRepository.delete(prodItem);
    }



    @Override
    public List<Product> searchProducts(String productName, String categoryName) throws InvalidFilterException {
        if (productName == null && categoryName == null) {
            throw new InvalidFilterException("At least one filter parameter must be provided.");
        }
        return productRepository.searchProducts(productName, categoryName);
    }
}
