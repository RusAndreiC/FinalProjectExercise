package com.exercise.exercise.dto.orderLine;


import com.exercise.exercise.model.product.Product;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrderLineResponse {

    private int id;
    private int quantity;
    private Product product;
}
