//package com.foodapi.betaecommerceapiv2.models.cart;
//
//import com.foodapi.betaecommerceapiv2.models.product.Product;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Entity
//@Table(name = "cart")
//public class Cart {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long cartId;
//
//    @Column(name = "userId")
//    private Long userId;
//
//    @Column(name = "dateCreated")
//    private Date dateCreated;
//
//    @Column(name = "dateUpdated")
//    private Date dateUpdated;
//
//    @Column(name = "totalPrice")
//    private Date totalPrice;
//
//    @OneToMany(mappedBy = "cartItem")
//    private CartItem cartItem;
//
//    // Default constructor
//
//    public Cart() {
//    }
//
//    // Constructor with parameters
//
//    public Cart(Long cartId, Long userId, Date dateCreated, Date dateUpdated, Date totalPrice, CartItem cartItem) {
//        this.cartId = cartId;
//        this.userId = userId;
//        this.dateCreated = dateCreated;
//        this.dateUpdated = dateUpdated;
//        this.totalPrice = totalPrice;
//        this.cartItem = cartItem;
//    }
//
//    // Getters and Setters
//
//    public Long getCartId() {
//        return cartId;
//    }
//
//    public void setCartId(Long cartId) {
//        this.cartId = cartId;
//    }
//
//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
//
//    public Date getDateCreated() {
//        return dateCreated;
//    }
//
//    public void setDateCreated(Date dateCreated) {
//        this.dateCreated = dateCreated;
//    }
//
//    public Date getDateUpdated() {
//        return dateUpdated;
//    }
//
//    public void setDateUpdated(Date dateUpdated) {
//        this.dateUpdated = dateUpdated;
//    }
//
//    public Date getTotalPrice() {
//        return totalPrice;
//    }
//
//    public void setTotalPrice(Date totalPrice) {
//        this.totalPrice = totalPrice;
//    }
//
//    public CartItem getCartItem() {
//        return cartItem;
//    }
//
//    public void setCartItem(CartItem cartItem) {
//        this.cartItem = cartItem;
//    }
//}
