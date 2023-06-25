package com.foodapi.betaecommerceapiv2.models.order;

//Data Structure for order

import com.foodapi.betaecommerceapiv2.models.customer.Customer;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="order")
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    /*
     represents the collection of order items associated with an order,
     allowing for multiple items to be stored within a single order.
    */
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "order_id",referencedColumnName = "id",insertable = false,updatable = false)
    private List<OrderItem> orderItems;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Customer customer;

    //default constructer
    public Order(){
        super();
    }

    //parameterized constructer
    public Order(Long id, Date createdDate, Double totalPrice, List<OrderItem> orderItems, Customer customer) {
        this.id = id;
        this.createdDate = createdDate;
        this.totalPrice = totalPrice;
        this.orderItems = orderItems;
        this.customer = customer;
    }

    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
