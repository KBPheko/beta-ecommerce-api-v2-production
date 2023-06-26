package com.foodapi.betaecommerceapiv2.controller.customer;

import com.foodapi.betaecommerceapiv2.exceptions.common.BadRequestException;
import com.foodapi.betaecommerceapiv2.exceptions.customer.*;
import com.foodapi.betaecommerceapiv2.models.requests.AuthenticationRequest;
import com.foodapi.betaecommerceapiv2.models.customer.Customer;
import com.foodapi.betaecommerceapiv2.security.JwtUtil;
import com.foodapi.betaecommerceapiv2.service.customer.CustomerService;
import com.foodapi.betaecommerceapiv2.service.jwt.JwtService;
import com.foodapi.betaecommerceapiv2.util.ResponseHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * This class is a controller for the customer endpoints
 */
@Tag(name = "Authentication", description = "These endpoints provides the capability to register and authenticate Customers")
@RestController
@RequestMapping("/api/ecommerce/customers")
public class CustomerController {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final JwtService jwtService;

    private final CustomerService customerService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, JwtService jwtService, CustomerService customerService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.jwtService = jwtService;
        this.customerService = customerService;
        this.passwordEncoder = passwordEncoder;
    }

    // This method is used to retrieve all customers
    @Operation(summary = "Retrieve all customers", description = "This endpoint is used to retrieve all customers")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved all users"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "You are not authorized to view the resource"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden")
    })
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Bearer <token>", required = true)
    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Object> getAllUsers() {
        return ResponseHandler.generateResponse("Successfully retrieved all Customers", HttpStatus.OK, customerService.getAllCustomers().join());
    }

    // This method is used to retrieve a customer by email
    @Operation(summary = "Retrieve a user by email", description = "This endpoint is used to retrieve a user by email")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved customer"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid email provided"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @Parameters(value = {
            @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Bearer <token>", required = true),
            @Parameter(name = "email", in = ParameterIn.PATH, description = "Email of the customer §§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§to be retrieved", required = true)
    })
    @GetMapping("/customer/{email}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Object> getUserByEmail(@PathVariable String email) throws BadRequestException, UserNotFoundException {
        return ResponseHandler.generateResponse("Successfully retrieved customer", HttpStatus.OK, customerService.getCustomerByEmail(email).join());
    }

    // This method is used to register a new customer
    @Operation(summary = "Register a customer", description = "This endpoint is used to register a customer")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Successfully registered customer"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Customer already exists")
    })
    @PostMapping("/customer")
    public ResponseEntity<Object> registerUser(@Validated @RequestBody Customer customer) throws UserExistsException {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerService.createCustomer(customer);
        return ResponseHandler.generateResponse("Successfully registered customer", HttpStatus.CREATED, null);
    }

    // This method is used to authenticate an in-coming customer
    @Operation(summary = "Authenticate a customer", description = "This endpoint is used to authenticate a customer")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully authenticated customer"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Invalid credentials"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @PostMapping("/customer/authentication")
    public ResponseEntity<Object> authenticateUser(@Validated @RequestBody AuthenticationRequest request) throws InvalidCredentialsException {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = jwtService.loadUserByUsername(request.getEmail());
            final String jwt = jwtUtil.generateToken(userDetails);
            return ResponseHandler.generateResponse("Successfully authenticated customer", HttpStatus.OK, jwt);
        } catch (UsernameNotFoundException e) {
            throw new InvalidCredentialsException("Invalid credentials");
        }
    }

    // This method is used to update an existing customer
    @Operation(summary = "Update a customer", description = "This endpoint is used to update a customer")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully updated customer"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @Parameters(value = {
            @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Bearer <token>", required = true),
            @Parameter(name = "email", in = ParameterIn.PATH, description = "Email of the customer to be updated", required = true)
    })
    @PutMapping("/customer/{email}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Object> updateUser(@PathVariable String email, @Validated @RequestBody Customer customer) throws UserNotFoundException {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerService.updateCustomer(email, customer);
        return ResponseHandler.generateResponse("Successfully updated customer", HttpStatus.OK, null);
    }

    // This method is used to delete a user
    @Operation(summary = "Delete a customer", description = "This endpoint is used to delete a customer")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully deleted customer"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @Parameters(value = {
            @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Bearer <token>", required = true),
            @Parameter(name = "email", in = ParameterIn.PATH, description = "Email of the customer to be deleted", required = true)
    })
    @DeleteMapping("/customer/{email}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Object> deleteUser(@PathVariable String email) throws UserNotFoundException {
        customerService.deleteCustomer(email);
        return ResponseHandler.generateResponse("Successfully deleted customer", HttpStatus.OK, null);
    }
}
