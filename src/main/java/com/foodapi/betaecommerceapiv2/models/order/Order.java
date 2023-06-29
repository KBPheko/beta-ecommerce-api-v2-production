package com.foodapi.betaecommerceapiv2.models.order;

//Data Structure for order
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * This class defines the order model
 */
@Entity
@Schema(name = "Order", description = "Order model")
@Table(name = "orders") // Renamed the table to "orders"
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Order id", example = "4")
    private Long id;

    @Column(name = "created_at")
    @Schema(description = "Order created at", example = "2022-04-11 00:00:00")
    private Date createdAt;

    @Column(name = "updated_at")
    @Schema(description = "Order updated at", example = "2022-04-14 00:00:00")
    private Date updatedAt;

    @Column(name = "customer")
    @Schema(description = "Order customer", example = "johnDoe@gmail.com")
    private String customer;

    @Column(name = "total_amount")
    @Schema(description = "Order total amount", example = "40.00")
    private Double total;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    public Order() {
        super();
    }

    public Order(Long id, Date createdAt, Date updatedAt, String customer, Double total, List<OrderItem> orderItems) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.customer = customer;
        this.total = total;
        this.orderItems = orderItems;
    }

    // Getters and setters...


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

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total= total;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
