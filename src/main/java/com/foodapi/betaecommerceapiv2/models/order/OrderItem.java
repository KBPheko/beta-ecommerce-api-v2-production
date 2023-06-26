package com.foodapi.betaecommerceapiv2.models.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.foodapi.betaecommerceapiv2.models.product.Product;
import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name ="order_item")
@Schema(name = "OrderItem", description = "OrderItem model")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "OrderItemId", example = "4")

    private Long id;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    @Schema(description = "Order item quantity", example = "1") //code for swagger
    private int quantity;

    @Column(name = "price")
    @Schema(description = "Order item price", example = "100.00")
    private double price;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "created_date")
    @Schema(description = "OrderItem createdDate", example = "2021-09-01 00:00:00")

    private Date createdDate;


//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name ="order_id")
//    private OrderModel order;


    public OrderItem() {
    }

    public OrderItem(Long id, Product product, int quantity, double price, Date createdDate, Order order) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.order = order;
        this.createdDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}



