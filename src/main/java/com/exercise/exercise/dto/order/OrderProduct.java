package com.exercise.exercise.dto.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderProduct {
    long productId;
    int productQuantity;
}
