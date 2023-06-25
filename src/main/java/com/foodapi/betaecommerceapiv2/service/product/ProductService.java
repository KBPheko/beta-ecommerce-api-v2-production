package com.foodapi.betaecommerceapiv2.service.product;

import com.foodapi.betaecommerceapiv2.exceptions.product.InvalidFilterException;
import com.foodapi.betaecommerceapiv2.exceptions.product.ProductExistsException;
import com.foodapi.betaecommerceapiv2.exceptions.product.ProductNotFoundException;
import com.foodapi.betaecommerceapiv2.models.product.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts() throws ProductNotFoundException;

    Product getProductById(Long productId) throws ProductNotFoundException;

    Product createProduct(Product product) throws ProductExistsException;

    Product updateExistingProduct(Long productId, Product product) throws ProductNotFoundException;

    void deleteProduct(Long productId) throws ProductNotFoundException;

    List<Product> searchProducts(String product, String categoryName) throws InvalidFilterException;
}
