package com.foodapi.betaecommerceapiv2.models.cart;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "cartId")
    private Cart cart;

    @Column(name = "productId")
    private Long productId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private Double price;

    public CartItem() {
        super();
    }

    public CartItem(Long id, Cart cart, Long productId, int quantity, Double price) {
        this.id = id;
        this.cart = cart;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    //Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
