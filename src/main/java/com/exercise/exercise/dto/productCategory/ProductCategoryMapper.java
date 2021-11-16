package com.exercise.exercise.dto.productCategory;

import com.exercise.exercise.model.product.Product;
import com.exercise.exercise.model.product.ProductCategory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
public class ProductCategoryMapper {
    public ProductCategoryResponse toDto(ProductCategory productCategory) {
        ProductCategoryResponse dto = new ProductCategoryResponse();
        dto.setId(productCategory.getId());
        dto.setName(productCategory.getName());
        dto.setProduct((Product) productCategory.getProducts());
        return dto;
    }

    public ProductCategory toEntity(ProductCategoryRequest productCategoryRequest) {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setName(productCategoryRequest.getName());
        productCategory.setProducts((List<Product>) productCategoryRequest.getProduct());
        return productCategory;
    }

    public ProductCategory toEntity(ProductCategory productCategoryToUpdate, ProductCategoryRequest updateRequest) {
        if (!StringUtils.isEmpty(updateRequest.getName())) {
            productCategoryToUpdate.setName(updateRequest.getName());
        }


        if (!StringUtils.isEmpty(updateRequest.getProduct())) {
            productCategoryToUpdate.setProducts((List<Product>) updateRequest.getProduct());
        }


        return productCategoryToUpdate;
    }
}
