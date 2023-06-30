package com.foodapi.betaecommerceapiv2.models.customer;

import javax.persistence.*;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Schema(description = "This is the customer model.")
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "This is the customer's id.")
    private Long id;

    @Column(name = "first_name", nullable = false, length = 50)
    @NotBlank(message = "First name is required.")
    @Schema(description = "This is the customer's first name.", example = "Mitchelle")
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    @NotBlank(message = "Last name is required.")
    @Schema(description = "This is the customer's last name.", example = "Doe")
    private String lastName;

    @Column(name = "email", nullable = false, length = 50)
    @NotBlank(message = "Email is required.")
    @Schema(description = "This is the customer's email.", example = "mitchelle@gmail.com")
    private String email;

    @Column(name = "password", nullable = false, length = 128)
    @NotBlank(message = "Password is required.")
    @Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters.")
    @Schema(description = "This is the customer's password.", example = "12345678")
    private String password;

    @Column(name = "contact_number", nullable = false, length = 10)
    @NotBlank(message = "Contact number is required.")
    @Size(min = 10, max = 10, message = "Contact number must be 10 characters.")
    @Schema(description = "This is the customer's contact number.", example = "12345678")
    private String contactNumber;

    @Column(name = "role", nullable = true, length = 20)
    @Schema(description = "This is the customer's role.", example = "ROLE_USER")
    private String role;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @Schema(description = "This is the customer's address.")
    private Address address;

    public Customer() {
        super();
    }

    public Customer(Long id, String firstName, String lastName, String email, String password, String contactNumber, String role, Address address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.contactNumber = contactNumber;
        this.address = address;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role= role;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
