package com.foodapi.betaecommerceapiv2.models.customer;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * This class defines the address model
 */
@Schema(description = "This is the address model.")
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "This is the address's id.", example = "1")
    private Long id;

    @Column(name = "building", nullable = true, length = 50)
    @NotBlank(message = "Building is required.")
    @Schema(description = "This is the address's building.", example = "House")
    private String building;

    @Column(name = "street", nullable = false, length = 50)
    @NotBlank(message = "Street is required.")
    @Schema(description = "This is the address's street.", example = "Abc Street")
    private String street;

    @Column(name = "suburb", nullable = false, length = 50)
    @NotBlank(message = "Suburb is required.")
    @Schema(description = "This is the address's suburb.", example = "Abc Suburb")
    private String suburb;

    @Column(name = "city", nullable = false, length = 50)
    @NotBlank(message = "City is required.")
    @Schema(description = "This is the address's city.", example = "Abc City")
    private String city;

    @Column(name = "postal_code", nullable = false, length = 4)
    @NotBlank(message = "Postal code is required.")
    @Size(min = 4, max = 4, message = "Postal code must be 4 characters.")
    @Schema(description = "This is the address's postal code.", example = "1234")
    private String postalCode;

    public Address() {
        super();
    }

    public Address(Long id, String building, String street, String suburb, String city, String postalCode) {
        this.id = id;
        this.building = building;
        this.street = street;
        this.suburb = suburb;
        this.city = city;
        this.postalCode = postalCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
