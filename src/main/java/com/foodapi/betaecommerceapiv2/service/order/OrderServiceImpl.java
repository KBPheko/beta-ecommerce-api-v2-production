
package com.foodapi.betaecommerceapiv2.service.order;

import com.foodapi.betaecommerceapiv2.exceptions.order.OrderNotFoundException;
import com.foodapi.betaecommerceapiv2.models.cart.dto.CartDto;
import com.foodapi.betaecommerceapiv2.models.cart.dto.CartItemDto;
import com.foodapi.betaecommerceapiv2.models.order.OrderItem;
import com.foodapi.betaecommerceapiv2.repository.order.OrderItemsRepository;
import com.foodapi.betaecommerceapiv2.service.cart.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodapi.betaecommerceapiv2.models.order.Order;
import com.foodapi.betaecommerceapiv2.models.product.Product;
import com.foodapi.betaecommerceapiv2.repository.order.OrderRepository;
import org.springframework.scheduling.annotation.Async;


import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

    /**
     * This class implements the methods that provides CRUD operations for the order table
     */
    @Service
    public class OrderServiceImpl implements OrderService {

         private final CartService cartService;

        private final OrderRepository orderRepository;

        private final OrderItemsRepository orderItemRepository;

        @Autowired
        public OrderServiceImpl(OrderRepository orderRepository, OrderItemsRepository orderItemRepository,CartService cartService) {
            this.cartService = cartService;
            this.orderRepository = orderRepository;
            this.orderItemRepository = orderItemRepository;
        }

        // This method checks out a user's cart items and creates an order
        @Async
        public void checkOut(String customer) {
            CompletableFuture<CartDto> cartDto = cartService.listCartItems(customer);

            List<CartItemDto> cartItemDtoList = cartDto.join().getCartItems();

            Order order = new Order();
            order.setCreatedAt(new Date());
            order.setUpdatedAt(new Date());
            order.setCustomer(customer);
            order.setTotalAmount(cartDto.join().getTotal());
            orderRepository.save(order);

            for (CartItemDto cartItemDto : cartItemDtoList) {
                OrderItem orderItem = new OrderItem();
                orderItem.setId(cartItemDto.getId());
                orderItem.setCreatedDate(new Date());
                orderItem.setPrice(cartItemDto.getProduct().getPrice());
                orderItem.setQuantity(cartItemDto.getQuantity()); // Fix: Set the quantity from the cart item
                orderItem.setOrder(order);
                orderItemRepository.save(orderItem);
            }

            cartService.deleteUserCartItems(customer);
        }

        // This method returns a list of orders for a particular user
        @Async
        public CompletableFuture<List<Order>> getAllOrders(String customer) {
            return CompletableFuture.completedFuture(orderRepository.findAllByCustomer(customer));
        }

        // This method returns a particular order
        @Async
        public CompletableFuture<Order> getOrder(Long id) throws OrderNotFoundException {
            CompletableFuture<Order> order = CompletableFuture.completedFuture(orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found")));
            return CompletableFuture.completedFuture(order.join());
        }
    }

