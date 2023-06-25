package com.foodapi.betaecommerceapiv2.service.order;

import com.foodapi.betaecommerceapiv2.models.order.OrderItem;
import com.foodapi.betaecommerceapiv2.repository.order.OrderItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface OrderService {

    @Autowired

    //calling a fucntion to save an iteam in a particular order
    void addOrderedProducts(OrderItem orderItem);
}
