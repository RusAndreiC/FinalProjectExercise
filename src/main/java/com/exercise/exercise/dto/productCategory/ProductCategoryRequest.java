package com.exercise.exercise.dto.productCategory;

import com.exercise.exercise.model.product.Product;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductCategoryRequest {
    private String name;
    private Product product;

}
