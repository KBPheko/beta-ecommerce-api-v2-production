//package com.foodapi.betaecommerceapiv2.models.cart;
//
//import javax.persistence.Id;
//
//
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "cart_item")
//public class CartItem {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "cartId")
//    private Cart cart;
//
//    @Column(name = "productId")
//    private Long productId;
//
//    @Column(name = "quantity")
//    private int quantity;
//
//    public CartItem() {
//        super();
//    }
//
//    public CartItem(Long id, Cart cart, Long productId, int quantity) {
//        this.id = id;
//        this.cart = cart;
//        this.productId = productId;
//        this.quantity = quantity;
//    }
//
//    //Getters and Setters
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Cart getCart() {
//        return cart;
//    }
//
//    public void setCart(Cart cart) {
//        this.cart = cart;
//    }
//
//    public Long getProductId() {
//        return productId;
//    }
//
//    public void setProductId(Long productId) {
//        this.productId = productId;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//}
