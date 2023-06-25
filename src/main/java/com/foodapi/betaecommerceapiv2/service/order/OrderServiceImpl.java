package com.foodapi.betaecommerceapiv2.service.order;

import com.foodapi.betaecommerceapiv2.models.order.OrderItem;
import com.foodapi.betaecommerceapiv2.repository.order.OrderItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceImpl {
    // injection for the order item
    @Autowired
    private OrderItemsRepository orderItemsRepository;

    // method to save an item in a particular order
    public void addOrderedProducts(OrderItem orderItem) {
        orderItemsRepository.save(orderItem);
    }
}
