package com.foodapi.betaecommerceapiv2.service.cart;

import com.foodapi.betaecommerceapiv2.exceptions.cart.CartNotFoundException;
import com.foodapi.betaecommerceapiv2.models.cart.dto.AddToCartDto;
import com.foodapi.betaecommerceapiv2.models.cart.dto.CartDto;
import com.foodapi.betaecommerceapiv2.models.product.Product;

import java.util.concurrent.CompletableFuture;

/**
 * This interface defines the methods that provides CRUD operations for the cart table
 */

public interface CartService {

    void addToCart(AddToCartDto addToCartDto, Product product, String user);/** an add to cart method that accepts three parameters**/

    CompletableFuture<CartDto> listCartItems(String user);

    void updateCartItem(AddToCartDto addToCartDto, Product product, String user) throws CartNotFoundException;

    void deleteCartItem(Long cartItemId, String user) throws CartNotFoundException; /** a method that deletes from the cart **/

    public void deleteUserCartItems(String user);
}
