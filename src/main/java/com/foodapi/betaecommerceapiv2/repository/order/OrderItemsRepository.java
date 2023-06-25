package com.foodapi.betaecommerceapiv2.repository.order;

import com.foodapi.betaecommerceapiv2.models.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItem, Long> {
}
