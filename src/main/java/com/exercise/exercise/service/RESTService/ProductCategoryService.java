package com.exercise.exercise.service.RESTService;



import com.exercise.exercise.dto.productCategory.ProductCategoryRequest;
import com.exercise.exercise.dto.productCategory.ProductCategoryResponse;

import java.util.List;
import java.util.Map;

public interface ProductCategoryService {
    List<ProductCategoryResponse> findAll();

    ProductCategoryResponse findById(Long id);

    ProductCategoryResponse save(ProductCategoryRequest createProductCategoryRequest);

    ProductCategoryResponse update(Long id, ProductCategoryRequest updateRequest);

    ProductCategoryResponse partialUpdate(Long id, Map<String, Object> updates);

    void delete(Long id);

    // find using filter
    List<ProductCategoryResponse> findByProduct(String productName);
}
