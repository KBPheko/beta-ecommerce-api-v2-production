package com.foodapi.betaecommerceapiv2.service.order;

import com.foodapi.betaecommerceapiv2.exceptions.order.OrderNotFoundException;
import com.foodapi.betaecommerceapiv2.models.order.Order;
import com.foodapi.betaecommerceapiv2.models.order.OrderItem;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface OrderService {
    //calling a fucntion to save an iteam in a particular order froor the order item
    void addOrderedProducts(OrderItem orderItem);

    //for checking out
    void checkOut(String customer);
    //getting a list of the order for a product
    CompletableFuture<List<Order>> getAllproducts(String customer);
    // throw an exception if the order is not found
    CompletableFuture<Order> getOrder(Long id) throws OrderNotFoundException;
}


