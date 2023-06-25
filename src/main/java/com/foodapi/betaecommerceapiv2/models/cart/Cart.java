package com.foodapi.betaecommerceapiv2.models.cart;

import com.foodapi.betaecommerceapiv2.models.product.Product;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "dateCreated")
    private Date dateCreated;

    @Column(name = "dateUpdated")
    private Date dateUpdated;

    @Column(name = "totalPrice")
    private Date totalPrice;

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CartItem> cartItems;

    // Default constructor
    public Cart() {
    }

    // Constructor with parameters
    public Cart(Long cartId, Long userId, Date dateCreated, Date dateUpdated, Date totalPrice, List<CartItem> cartItems) {
        this.cartId = cartId;
        this.userId = userId;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.totalPrice = totalPrice;
        this.cartItems = cartItems;
    }

    // Getters and Setters
    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Date getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Date totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CartItem> getCartItem() {
        return cartItems;
    }

    public void setCartItem(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
