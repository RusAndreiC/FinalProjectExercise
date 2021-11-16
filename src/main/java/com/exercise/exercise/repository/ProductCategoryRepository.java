package com.exercise.exercise.repository;

import com.exercise.exercise.model.product.ProductCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ProductCategoryRepository extends CrudRepository <ProductCategory, Long>{

    List<ProductCategory> findAll();

    Optional<ProductCategory> findByNameIgnoreCase(String name);
}
