package com.exercise.exercise.dto.product;

import com.exercise.exercise.model.product.Product;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Component
public class ProductMapper {

    public ProductResponse toDto(Product product) {
        ProductResponse dto = new ProductResponse();
        dto.setId(product.getId());
        dto.setDescription(product.getDescription());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setQuantity(product.getQuantity());
        dto.setCategory(product.getCategory());
        dto.setThumbnail(product.getThumbnail());
        return dto;
    }

    public Product toEntity(ProductRequest productRequest) {
        Product product = new Product();
        product.setDescription(productRequest.getDescription());
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());
        product.setCategory(productRequest.getCategory());
        product.setThumbnail(productRequest.getThumbnail());
        return product;
    }

    public Product toEntity(Product productToUpdate, ProductRequest updateRequest) {
        if (!StringUtils.isEmpty(updateRequest.getDescription())) {
            productToUpdate.setDescription(updateRequest.getDescription());
        }

        if (!StringUtils.isEmpty(updateRequest.getName())) {
            productToUpdate.setName(updateRequest.getName());
        }

        if (!StringUtils.isEmpty(updateRequest.getPrice())) {
            productToUpdate.setPrice(updateRequest.getPrice());
        }

        if (!StringUtils.isEmpty(updateRequest.getQuantity())) {
            productToUpdate.setQuantity(updateRequest.getQuantity());
        }

        if (!StringUtils.isEmpty(updateRequest.getCategory())) {
            productToUpdate.setCategory(updateRequest.getCategory());
        }

        if (!StringUtils.isEmpty(updateRequest.getThumbnail())) {
            productToUpdate.setThumbnail(updateRequest.getThumbnail());
        }

        
//        if (updateRequest.getPublished() != null && updateRequest.getPublished().isBefore(LocalDate.now())) {
//            productToUpdate.setPublished(updateRequest.getPublished());
//        }
        return productToUpdate;
    }
}
