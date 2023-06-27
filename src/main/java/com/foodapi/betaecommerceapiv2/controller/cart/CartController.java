package com.foodapi.betaecommerceapiv2.controller.cart;



import com.foodapi.betaecommerceapiv2.exceptions.cart.CartNotFoundException;
import com.foodapi.betaecommerceapiv2.exceptions.product.ProductNotFoundException;
import com.foodapi.betaecommerceapiv2.models.cart.dto.AddToCartDto;
import com.foodapi.betaecommerceapiv2.models.product.Product;
import com.foodapi.betaecommerceapiv2.service.cart.CartService;
import com.foodapi.betaecommerceapiv2.service.product.ProductService;
import com.foodapi.betaecommerceapiv2.util.ResponseHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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

import java.util.concurrent.CompletableFuture;


/**
 * This class is a controller for the cart endpoints
 */
@Tag(name = "Cart", description = "These endpoints provides the capability to add, update and delete cart items")
@RestController
@RequestMapping("/api/food/carts")
public class CartController {

    private final CartService cartService;

    private final ProductService productService;

    @Autowired
    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    // This is an endpoint for deleting a cart item
    @Operation(summary = "Delete a cart item", description = "This endpoint is used to delete a cart item", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Cart item deleted successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Cart not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Object> deleteCartItem(@PathVariable Long id) throws CartNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();
        cartService.deleteCartItem(id, user);
        return ResponseHandler.generateResponse("Cart item deleted successfully", HttpStatus.OK, null);
    }




    // This is an endpoint for listing all the cart items
    @Operation(summary = "List all cart items", description = "This endpoint is used to list all the cart items", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Cart items retrieved successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Cart not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public CompletableFuture<ResponseEntity<Object>> listCartItems() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();
        return cartService.listCartItems(user).thenApply(cartDto -> ResponseHandler.generateResponse("Cart items retrieved successfully", HttpStatus.OK, cartDto));
    }


    // This is an endpoint for updating a cart item
    @Operation(summary = "Update a cart item", description = "This endpoint is used to update a cart item", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Cart item updated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Cart not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Object> updateCartItem(@PathVariable Long id, @RequestBody AddToCartDto cartDto) throws CartNotFoundException, ProductNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();
        //CompletableFuture<Product> product = productService.getProductById(cartDto.getProductId());
        //cartService.updateCartItem(cartDto, product.join(), user);
        return ResponseHandler.generateResponse("Cart item updated successfully", HttpStatus.OK, null);
    }

    // This is an endpoint for adding a product to the cart
    @Operation(summary = "Add a product to the cart", description = "This endpoint is used to add a product to the cart", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Product added to cart successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Product not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })


    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Object> addToCart(@RequestBody AddToCartDto addToCartDto) throws ProductNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();
        // CompletableFuture<Product> product = productService.getProductById(addToCartDto.getProductId());
        //cartService.addToCart(addToCartDto, product.join(), user);
        return ResponseHandler.generateResponse("Product added to cart successfully", HttpStatus.OK, null);
    }



}
