package com.exercise.exercise.service.RESTService;



import com.exercise.exercise.dto.product.ProductRequest;
import com.exercise.exercise.dto.product.ProductResponse;

import java.util.List;
import java.util.Map;

public interface ProductService {
    List<ProductResponse> findAll();

    ProductResponse findById(Long id);

    ProductResponse save(ProductRequest createProductRequest);

    ProductResponse update(Long id, ProductRequest updateRequest);

    ProductResponse partialUpdate(Long id, Map<String, Object> updates);

    void delete(Long id);

    // find using filter
    List<ProductResponse> findByCategory(String categoryName);

}
