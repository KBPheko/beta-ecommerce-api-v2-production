package com.foodapi.betaecommerceapiv2.models.cart;


import com.foodapi.betaecommerceapiv2.models.product.Product;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import java.util.Date;

/**
 * This class defines the cart model
 */
@Entity
@Schema(name = "Cart", description = "Cart model")
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Cart id", example = "1")
    private Long id;

    @Column(name = "created_at")
    @Schema(description = "Cart created at", example = "2021-09-01 00:00:00")
    private Date createdAt;

    @Column(name = "updated_at")
    @Schema(description = "Cart updated at", example = "2021-09-01 00:00:00")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "customer")
    @Schema(description = "Cart customer", example = "abc123@gmail.com")
    private String customer;

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER,cascade = CascadeType.ALL )
    private CartItem cartItem;
    @Column(name = "quantity")
    @Schema(description = "Cart quantity", example = "1")
    private Integer quantity;

    public Cart() {
        super();
    }

    public Cart(Product product, String customer, Integer quantity) {
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.product = product;
        this.customer = customer;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}