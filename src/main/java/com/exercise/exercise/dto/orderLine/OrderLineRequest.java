package com.exercise.exercise.dto.orderLine;

import com.exercise.exercise.model.product.Product;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class OrderLineRequest {
    private int quantity;
    private Product product;
}
