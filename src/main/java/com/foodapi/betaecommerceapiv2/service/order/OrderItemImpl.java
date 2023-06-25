package com.foodapi.betaecommerceapiv2.service.order;

import com.foodapi.betaecommerceapiv2.models.order.OrderItem;

public interface OrderItemImpl {
    //calling a fucntion to save an iteam in a particular order
    void addOrderedProducts(OrderItem orderItem);
}



