package com.ideas2it.fooddeliverymanagement.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Order Model
 * Used to store the values of order
 *
 * @author Devaraj
 * @version 1.0
 * @since Dec 12 2022
 */
@Entity
@Getter
@Setter
@Table(name = "food_order")
public class Order extends BaseModel {
    private String status;
    private LocalDate dateOfOrder;
    private int deliveryId;
    private float totalPrice;
    @OneToMany(targetEntity = OrderDetail.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderDetail> orderDetails;
    @ManyToOne(fetch = FetchType.LAZY)
    private User customer;
    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;
}




