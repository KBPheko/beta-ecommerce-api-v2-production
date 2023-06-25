package com.foodapi.betaecommerceapiv2.service.order;

import com.foodapi.betaecommerceapiv2.models.order.OrderItem;
import com.foodapi.betaecommerceapiv2.repository.order.OrderItemsRepository;

public class OrderServiceImpl {

    private OrderItemsRepository orderItemsRepository;

    // method to save an item in a particular order
    public void addOrderedProducts(OrderItem orderItem) {
        orderItemsRepository.save(orderItem);
    }
git
}
