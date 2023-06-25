package com.foodapi.betaecommerceapiv2.controller.product;

import com.foodapi.betaecommerceapiv2.exceptions.APIResponseHandler;
import com.foodapi.betaecommerceapiv2.exceptions.ApiError;
import com.foodapi.betaecommerceapiv2.exceptions.common.BadRequestException;
import com.foodapi.betaecommerceapiv2.exceptions.product.InvalidFilterException;
import com.foodapi.betaecommerceapiv2.exceptions.product.ProductExistsException;
import com.foodapi.betaecommerceapiv2.exceptions.product.ProductNotFoundException;
import com.foodapi.betaecommerceapiv2.models.product.Product;
import com.foodapi.betaecommerceapiv2.service.product.ProductService;
import com.foodapi.betaecommerceapiv2.util.ResponseHandler;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/api/product")
public class ProductController {

    private ProductService productService;

    // Inject service
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /** To retrieve all products*/
    @GetMapping("/products")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Products Retrieved Successfully!"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden")
    })
    public ResponseEntity<Object> getAllProducts() throws ProductNotFoundException {
        List<Product> listOfProducts = productService.getAllProducts();
        if (listOfProducts.isEmpty()){
            throw new ProductNotFoundException("Products Not Found");
        }
        return ResponseHandler.generateResponse("Products Retrieved Successfully!", HttpStatus.OK, listOfProducts);
    }

    /** Retrieve product by ID*/
    @GetMapping("/{productId}")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Retrieved Product Successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid productId provided"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<Object> getProductById(@PathVariable Long productId) throws BadRequestException, ProductNotFoundException{
        return ResponseHandler.generateResponse("Retrieved Product Successfully", HttpStatus.OK, productService.getProductById(productId));
    }

    /** Create new product*/
    @PostMapping("/product")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully created product"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input provided"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Product already exists")
    })
    public ResponseEntity<Object> createProduct(@Validated @RequestBody Product product) throws ProductExistsException {
        return ResponseHandler.generateResponse("Successfully created product", HttpStatus.CREATED, productService.createProduct(product));
    }

    /** Update existing product*/
    @PatchMapping("/update/{productId}")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully updated product"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input provided"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<Object> updateProduct(@PathVariable Long productId, @Validated @RequestBody Product product) throws ProductNotFoundException{
        Product updatedProd = productService.updateExistingProduct(productId, product);
        return ResponseHandler.generateResponse("Successfully updated product", HttpStatus.OK, updatedProd);
    }

    /** Delete existing product*/
    @DeleteMapping("/delete/{productId}")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully deleted product"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input provided"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Product Not Found")
    })
    public ResponseEntity<Object> deleteProduct(@PathVariable Long productId) throws ProductNotFoundException {
        return ResponseHandler.generateResponse("Successfully deleted product", HttpStatus.OK, null);
    }

    /** Search and Filter products*/
    @GetMapping("/search")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Search success"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input provided"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Invalid Search")
    })
    public ResponseEntity<Object> searchProducts(@RequestParam(required = false) String productName,
                                                 @RequestParam(required = false) String categoryName) throws InvalidFilterException {
        List<Product> searchResults = productService.searchProducts(productName, categoryName);
        return ResponseHandler.generateResponse("Search success", HttpStatus.OK, searchResults);
    }
}
