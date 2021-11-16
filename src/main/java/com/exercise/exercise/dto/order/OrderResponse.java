package com.exercise.exercise.dto.order;


import com.exercise.exercise.model.cartNorder.OrderLine;
import com.exercise.exercise.model.cartNorder.OrderStatus;
import com.exercise.exercise.model.user.Address;
import com.exercise.exercise.model.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderResponse {

    private int id;
    private double totalPrice;
    private Address address;
    private Date orderDate;
    private List<OrderLine> entries = new ArrayList<OrderLine>();
    private User user;
    private OrderStatus orderStatus;
}
