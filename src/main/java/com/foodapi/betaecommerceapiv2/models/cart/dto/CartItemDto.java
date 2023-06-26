package com.foodapi.betaecommerceapiv2.models.cart.dto;

import com.foodapi.betaecommerceapiv2.models.cart.Cart;
import com.foodapi.betaecommerceapiv2.models.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * This is the DTO class for a cart item
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {
    private Long id;

    private Integer quantity;

    private Product product;


    public CartItemDto(Cart cart) {
        this.setId(cart.getId());
        this.setQuantity(cart.getQuantity());
        this.setProduct(cart.getProduct());
    }
}
