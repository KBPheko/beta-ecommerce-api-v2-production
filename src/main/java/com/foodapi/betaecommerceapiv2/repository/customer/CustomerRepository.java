package com.foodapi.betaecommerceapiv2.repository.customer;

import com.foodapi.betaecommerceapiv2.models.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This class is a repository for querying the customer table
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByEmail(String email);

    Customer findCustomerByEmail(String email);

    void deleteByEmail(String email);
}