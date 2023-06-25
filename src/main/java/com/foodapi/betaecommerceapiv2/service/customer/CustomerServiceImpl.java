package com.foodapi.betaecommerceapiv2.service.customer;

import com.foodapi.betaecommerceapiv2.exceptions.common.BadRequestException;
import com.foodapi.betaecommerceapiv2.exceptions.customer.UserExistsException;
import com.foodapi.betaecommerceapiv2.exceptions.customer.UserNotFoundException;
import com.foodapi.betaecommerceapiv2.models.customer.Customer;
import com.foodapi.betaecommerceapiv2.repository.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // This method is used to get all the customers
    @Async
    public CompletableFuture<List<Customer>> getAllCustomers() {
        return CompletableFuture.completedFuture(customerRepository.findAll());
    }

    // This method is used to get a customer by email
    @Async
    public CompletableFuture<Customer> getCustomerByEmail(String email) throws BadRequestException, UserNotFoundException {
        if (email == null || email.isEmpty()) {
            throw new BadRequestException("Email cannot be empty");
        }
        CompletableFuture<Customer> customer = CompletableFuture.completedFuture(customerRepository.findCustomerByEmail(email));
        if (customer == null) {
            throw new UserNotFoundException("User with email " + email + " not found");
        }
        return CompletableFuture.completedFuture(customerRepository.findByEmail(email));
    }

    // This method is used to create a customer
    @Async
    public void createCustomer(Customer customer) throws UserExistsException {
        Customer customerExists = customerRepository.findCustomerByEmail(customer.getEmail());
        if (customerExists != null) {
            throw new UserExistsException("User with email " + customer.getEmail() + " already exists");
        }
        customer.setRole("USER");
        customerRepository.save(customer);
    }

    // This method is used to update a customer
    @Async
    public void updateCustomer(String email, Customer customer) throws UserNotFoundException {
        Customer customerToUpdate = customerRepository.findCustomerByEmail(email);
        if (customerToUpdate == null) {
            throw new UserNotFoundException("User with email " + email + " not found");
        }
        customerToUpdate.setFirstName(customer.getFirstName());
        customerToUpdate.setLastName(customer.getLastName());
        customerToUpdate.setEmail(customer.getEmail());
        customerToUpdate.setPassword(customer.getPassword());
        customerToUpdate.setContactNumber(customer.getContactNumber());
        customerToUpdate.setAddress(customer.getAddress());
        customerRepository.save(customerToUpdate);
    }

    // This method is used to delete a customer
    @Async
    public void deleteCustomer(String email) throws UserNotFoundException {
        Customer customerToDelete = customerRepository.findCustomerByEmail(email);
        if (customerToDelete == null) {
            throw new UserNotFoundException("User with email " + email + " not found");
        }
        customerRepository.delete(customerToDelete);
    }
}
