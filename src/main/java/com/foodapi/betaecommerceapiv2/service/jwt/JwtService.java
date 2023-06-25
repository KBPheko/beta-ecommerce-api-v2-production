package com.foodapi.betaecommerceapiv2.service.jwt;

import com.foodapi.betaecommerceapiv2.models.customer.Customer;
import com.foodapi.betaecommerceapiv2.repository.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides methods for loading and generating the jwt payload
 */
@Service
public class JwtService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Autowired
    public JwtService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // This method is used to load a user by email
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository.findCustomerByEmail(email);
        if (customer == null) {
            throw new UsernameNotFoundException("Customer not found with email: " + email);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new User(customer.getEmail(), customer.getPassword(), authorities);
    }

    // This method is used to create the user details
    public UserDetails createUserDetails(String email, String password) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new User(email, password, authorities);
    }
}
