package com.foodapi.betaecommerceapiv2.controller.order;

import com.foodapi.betaecommerceapiv2.exceptions.order.OrderNotFoundException;
import com.foodapi.betaecommerceapiv2.models.order.Order;
import com.foodapi.betaecommerceapiv2.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/api/orders")
public class OrderController {
 //reponsible for the Restful APi

    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    // get all orders
    @GetMapping
    public ResponseEntity<Object> listAllOrders() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();
        CompletableFuture<List<Order>> orders = orderService.getAllOrders(user);
        if (orders.join().isEmpty()) {
            return ResponseHandler.generateResponse("No orders found", HttpStatus.NOT_FOUND, null);
        }
        return ResponseHandler.generateResponse("Orders retrieved successfully", HttpStatus.OK, orders.join());
    }
//get one product by id
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrder(@PathVariable Long id) throws OrderNotFoundException {
        CompletableFuture<Order> order = orderService.getOrder(id);
        return ResponseHandler.generateResponse("Order retrieved successfully", HttpStatus.OK, order.join());
    }

    //add an order
    @PostMapping
    public ResponseEntity<Object> placeOrder() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();
        orderService.checkOut(user);
        return ResponseHandler.generateResponse("Order placed successfully", HttpStatus.CREATED, null);
    }



}
