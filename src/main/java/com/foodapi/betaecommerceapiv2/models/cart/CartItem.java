package com.foodapi.betaecommerceapiv2.models.cart;

<<<<<<< HEAD
import javax.persistence.Id;


public class CartItem {

=======
import javax.persistence.*;

@Entity
@Table(name = "cart_item")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cartId")
    private Cart cart;

    @Column(name = "productId")
    private Long productId;

    @Column(name = "quantity")
    private int quantity;

    public CartItem() {
        super();
    }

    public CartItem(Long id, Cart cart, Long productId, int quantity) {
        this.id = id;
        this.cart = cart;
        this.productId = productId;
        this.quantity = quantity;
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
>>>>>>> 7d2a9f14222fd50f692c17e259064bef966a115a
}
