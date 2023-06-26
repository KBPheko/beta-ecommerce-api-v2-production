package com.foodapi.betaecommerceapiv2.repository.cart;

import com.foodapi.betaecommerceapiv2.models.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByCustomer(String user);

    List<Cart> deleteByCustomer(String user);
}
