package com.foodapi.betaecommerceapiv2.models.cart.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This is the DTO(stands for Data Transfer Object) class for the add to cart request
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddToCartDto {

    private Long id;

    private Long productId;

    private Integer quantity;
}