//package com.foodapi.betaecommerceapiv2.models.order;
//
////Data Structure for order
//
////import io.swagger.v3.oas.annotations.media.Schema;
//
//import javax.persistence.*;
//import java.util.Date;
//import java.util.List;
//
///**
// * This class defines the order model
// */
//@Entity
////@Schema(name = "Order", description = "Order model")
//@Table(name = "order")
//public class Order {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    //@Schema(description = "Order id", example = "1")
//    private Long id;
//
//    @Column(name = "created_at")
//    //@Schema(description = "Order created at", example = "2022-04-11 00:00:00")
//    private Date createdAt;
//
//    @Column(name = "updated_at")
//    //@Schema(description = "Order updated at", example = "2022-04-14 00:00:00")
//    private Date updatedAt;
//
//    @Column(name = "customer")
//   // @Schema(description = "Order customer", example = "johnDoe@gmail.com")
//    private String customer;
//
//    @Column(name = "total_amount")
//    //@Schema(description = "Order total amount", example = "100.00")
//    private Double totalAmount;
//
//    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private List<OrderItem> orderItems;
//
//    public Order(){
//        super();
//    }
//
//    public Order(Long id, Date createdAt, Date updatedAt, String customer, Double totalAmount, List<OrderItem> orderItems) {
//        this.id = id;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
//        this.customer = customer;
//        this.totalAmount = totalAmount;
//        this.orderItems = orderItems;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Date getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Date createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public Date getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(Date updatedAt) {
//        this.updatedAt = updatedAt;
//    }
//
//    public String getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(String customer) {
//        this.customer = customer;
//    }
//
//    public Double getTotalAmount() {
//        return totalAmount;
//    }
//
//    public void setTotalAmount(Double totalAmount) {
//        this.totalAmount = totalAmount;
//    }
//
//    public List<OrderItem> getOrderItems() {
//        return orderItems;
//    }
//
//    public void setOrderItems(List<OrderItem> orderItems) {
//        this.orderItems = orderItems;
//    }
//}
//
