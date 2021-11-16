package com.exercise.exercise.dto.product;

import com.exercise.exercise.model.product.ProductCategory;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductResponse {

    private Long id;

    private String name;
    private int quantity;
    private String description;
    private String thumbnail;
    private double price;
    private ProductCategory category;


}
