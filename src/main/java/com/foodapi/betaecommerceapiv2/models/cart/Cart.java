package com.foodapi.betaecommerceapiv2.models.cart;


import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * This class defines the cart model
 */
@Entity
@Schema(name = "Cart", description = "Cart model")
@Table(name = "cart")
public class Cart {

}