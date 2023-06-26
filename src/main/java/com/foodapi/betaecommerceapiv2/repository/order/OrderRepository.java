package com.foodapi.betaecommerceapiv2.repository.order;

import com.foodapi.betaecommerceapiv2.models.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByCustomer(String customer);
}
