package com.foodapi.betaecommerceapiv2.models.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * This is the DTO(stands for Data Transfer Object)  class for the cart
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

    private List<CartItemDto> cartItems; //cartItems as a list which makes use of the CartItemDto class

    private Double total;

}
