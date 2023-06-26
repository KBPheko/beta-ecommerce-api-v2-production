package com.foodapi.betaecommerceapiv2.controller.product;

import com.foodapi.betaecommerceapiv2.exceptions.common.BadRequestException;
import com.foodapi.betaecommerceapiv2.exceptions.product.InvalidFilterException;
import com.foodapi.betaecommerceapiv2.exceptions.product.ProductExistsException;
import com.foodapi.betaecommerceapiv2.exceptions.product.ProductNotFoundException;
import com.foodapi.betaecommerceapiv2.models.product.Product;
import com.foodapi.betaecommerceapiv2.service.product.ProductServiceImpl;
import com.foodapi.betaecommerceapiv2.util.ResponseHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/api/product")
public class ProductController {

    private final ProductServiceImpl productService;

    Logger logger = LoggerFactory.getLogger(ProductController.class);

    // Inject service
    @Autowired
    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    /** To retrieve all products*/
    @GetMapping("/products")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Products Retrieved Successfully!"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden")
    })
    public ResponseEntity<Object> getAllProducts() {
        List<Product> listOfProducts = productService.getAllProducts();
        if (!listOfProducts.isEmpty()){
            return ResponseHandler.generateResponse("Products Retrieved Successfully!", HttpStatus.OK, listOfProducts);
        } else {
            return ResponseHandler.generateResponse("No Products Found", HttpStatus.NO_CONTENT, null);
        }
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
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully created product"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input provided"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Product already exists")
    })
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/create")
    public ResponseEntity<Object> createProduct(@Validated @RequestBody Product product) throws ProductExistsException {
        List<Product> products = productService.getAllProducts();
        for (Product product1: products){
            if (product1.getProductName().equals(product.getProductName())){
                throw new ProductExistsException("Product already exists");
            }
        }
        return ResponseHandler.generateResponse("Successfully created product", HttpStatus.CREATED, productService.createProduct(product));
    }

    /** Update existing product*/
    @PatchMapping("/update/{productId}")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully updated product"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input provided"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Product not found")
    })
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Object> updateProduct(@PathVariable Long productId, @Validated @RequestBody Product product) throws ProductNotFoundException{
        Product updatedProd = productService.updateExistingProduct(productId, product);
        return ResponseHandler.generateResponse("Successfully updated product", HttpStatus.OK, updatedProd);
    }

    @DeleteMapping("/delete/{productId}")
    @Operation(security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully deleted product"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input provided"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Product Not Found")
    })
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long productId) throws ProductNotFoundException, BadRequestException {
        try {
            productService.deleteProduct(productId);
            return ResponseHandler.generateResponse("Successfully deleted product", HttpStatus.OK, null);
        } catch (ProductNotFoundException e) {
            //return ResponseHandler.generateResponse("Product Not Found", HttpStatus.NOT_FOUND, e.getLocalizedMessage());
            throw new ProductNotFoundException("Product Not Found");
        } catch (Exception e) {
            throw new BadRequestException("Internal Server Error");
        }
    }


    /** Search and Filter products*/
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Search success"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input provided"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Invalid Search")
    })
    @GetMapping("/search")
    public ResponseEntity<Object> searchProducts(@RequestParam(required = false) String productName,
                                                 @RequestParam(required = false) String categoryName) throws InvalidFilterException {
        try {
            logger.info("Received search request with productName: {} and categoryName: {}", productName, categoryName);

            List<Product> searchResults = productService.searchProducts(productName, categoryName);

            if (searchResults.isEmpty()) {
                logger.info("Search results: Not found");
                return ResponseHandler.generateResponse("Not Found!!!", HttpStatus.NOT_FOUND, null);
            } else {
                logger.info("Search results size: {}", searchResults.size());
                return ResponseHandler.generateResponse("Search Results", HttpStatus.FOUND, searchResults);
            }
        } catch (Exception e) {
            String errorMsg = e.getLocalizedMessage();
            errorMsg = "INTERNAL SERVER ERROR!!!";
            logger.error("Error occurred during search", e);
            return ResponseHandler.generateResponse(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
