package com.foodapi.betaecommerceapiv2.controller.order;

import com.foodapi.betaecommerceapiv2.exceptions.order.OrderNotFoundException;
import com.foodapi.betaecommerceapiv2.models.order.Order;
import com.foodapi.betaecommerceapiv2.service.order.OrderService;
import com.foodapi.betaecommerceapiv2.util.ResponseHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Tag(name = "Order", description = "These endpoints demonstrate the ability view all orders, retrieve order by ID, and place orders")
@RestController
@RequestMapping(value = "/api/v2/orders")
public class OrderController {
 //responsible for the Restful APi


    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // get all orders
    @Operation(summary = "List of orders", description = "This endpoint is used to view a list of orders", security = @SecurityRequirement(name =
            "bearerAuth"))
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Retrieved orders successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not found")
    })
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping(value = "/orders")
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
@Operation(summary = "Get order by ID", description = "API endpoint used to get order by ID", security = @SecurityRequirement(name =
        "bearerAuth"))
@ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "GET order successfully"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not found")
})
@PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrder(@PathVariable Long id) throws OrderNotFoundException {
        CompletableFuture<Order> order = orderService.getOrder(id);
        return ResponseHandler.generateResponse("Order retrieved successfully", HttpStatus.OK, order.join());
    }

    //add an order
    @Operation(summary = "Place an order", description = "API endpoint used to place an order", security = @SecurityRequirement(name =
    "bearerAuth"))
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Ordered successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Unauthorized"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not found")
    })
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping(value ="/order")
    public ResponseEntity<Object> placeOrder() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();
        orderService.checkOut(user);
        return ResponseHandler.generateResponse("Ordered successfully", HttpStatus.CREATED, null);
    }
}
