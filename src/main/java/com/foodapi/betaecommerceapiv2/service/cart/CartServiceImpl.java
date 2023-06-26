package com.foodapi.betaecommerceapiv2.service.cart;


import com.foodapi.betaecommerceapiv2.exceptions.cart.CartNotFoundException;
import com.foodapi.betaecommerceapiv2.models.cart.Cart;
import com.foodapi.betaecommerceapiv2.models.cart.dto.AddToCartDto;
import com.foodapi.betaecommerceapiv2.models.cart.dto.CartDto;
import com.foodapi.betaecommerceapiv2.models.cart.dto.CartItemDto;
import com.foodapi.betaecommerceapiv2.models.product.Product;
import com.foodapi.betaecommerceapiv2.repository.cart.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;


/**
 * This class implements the methods that provides CRUD operations for the cart table
 */
@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    // This method converts a cart object to a cart item dto
    public static CartItemDto getDtoFromCart(Cart cart) {
        return new CartItemDto(cart);
    }

    // This method converts a cart item dto to a cart object
    @Async
    public void addToCart(AddToCartDto addToCartDto, Product product, String user) {
        Cart cart = new Cart(product, user, addToCartDto.getQuantity());
        cartRepository.save(cart);
    }

    // This method returns a list of cart items for a particular user
    @Async
    public CompletableFuture<CartDto> listCartItems(String user) {
        List<Cart> cartList = cartRepository.findAllByCustomer(user);
        List<CartItemDto> cartItems = new ArrayList<>();
        for (Cart cart: cartList) {
            CartItemDto cartItemDto = getDtoFromCart(cart);
            cartItems.add(cartItemDto);
        }
        double totalCost = 0;
        for (CartItemDto cartItemDto: cartItems) {
            totalCost += (cartItemDto.getQuantity() * cartItemDto.getProduct().getPrice());
        }
        CartDto cartDto = new CartDto(cartItems, totalCost);
        return CompletableFuture.completedFuture(cartDto);
    }

    // This method updates a cart item
    @Async
    public void updateCartItem(AddToCartDto cartDto, Product product, String customer) throws CartNotFoundException {
        Cart cart = cartRepository.findById(cartDto.getId()).orElseThrow(() -> new CartNotFoundException("Cart item not found"));
        cart.setQuantity(cartDto.getQuantity());
        cart.setCreatedAt(new Date());
        cart.setUpdatedAt(new Date());
        cartRepository.save(cart);
    }

    // This method deletes a cart item
    @Async
    public void deleteCartItem(Long cartItemId, String user) throws CartNotFoundException {
        if (!cartRepository.existsById(cartItemId)) {
            throw new CartNotFoundException("Cart item not found");
        }
        cartRepository.deleteById(cartItemId);
    }

    // This method deletes all cart items for a particular user
    @Async
    public void deleteUserCartItems(String user) {
        cartRepository.deleteByCustomer(user);
    }

}
