package com.foodapi.betaecommerceapiv2.service.product;

import com.foodapi.betaecommerceapiv2.exceptions.product.InvalidFilterException;
import com.foodapi.betaecommerceapiv2.exceptions.product.ProductExistsException;
import com.foodapi.betaecommerceapiv2.exceptions.product.ProductNotFoundException;
import com.foodapi.betaecommerceapiv2.models.product.Category;
import com.foodapi.betaecommerceapiv2.models.product.Product;
import com.foodapi.betaecommerceapiv2.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
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
        if (allProducts.isEmpty()){
            return null;
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
    public Product createProduct(Product product) throws ProductExistsException {
        Product savedProduct = productRepository.save(product);
        if (savedProduct != null){
            throw new ProductExistsException("Product Already Exists!");
        }
        return savedProduct;
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
    @Override
    public void deleteProduct(Long productId) throws ProductNotFoundException {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product Not Found"));

        productRepository.deleteById(productId);
    }

    @Override
    public List<Product> searchProducts(String productName, String categoryName) throws InvalidFilterException {
        if (productName == null && categoryName == null){
            throw new InvalidFilterException("At least one filer parameter must be provided.");
        }
        return productRepository.searchProduct(productName, categoryName);
    }
}
