package com.foodapi.betaecommerceapiv2.service.customer;

import com.foodapi.betaecommerceapiv2.exceptions.common.BadRequestException;
import com.foodapi.betaecommerceapiv2.exceptions.customer.UserExistsException;
import com.foodapi.betaecommerceapiv2.exceptions.customer.UserNotFoundException;
import com.foodapi.betaecommerceapiv2.models.customer.Customer;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CustomerService {

    CompletableFuture<List<Customer>> getAllCustomers();

    CompletableFuture<Customer> getCustomerByEmail(String email) throws BadRequestException, UserNotFoundException;

    void createCustomer(Customer customer) throws UserExistsException;

    void updateCustomer(String email, Customer customer) throws UserNotFoundException;

    void deleteCustomer(String email) throws UserNotFoundException;
}
