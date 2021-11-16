package com.exercise.exercise.model.cartNorder;

import com.exercise.exercise.model.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int quantity;

    @ManyToOne
    private Product product;

    @ManyToOne
    @JoinTable(name = "order_orderlines",
            joinColumns = @JoinColumn(name = "orderline_id"),
            inverseJoinColumns = @JoinColumn(name = "orders_id"))
    private Orders orders;

    private double price;

    public OrderLine(int quantity, Product product, Orders orders, double price) {
        this.quantity = quantity;
        this.product = product;
        this.orders = orders;
        this.price = price;
    }
}