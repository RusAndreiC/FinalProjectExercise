package com.exercise.exercise.service.MVCService;


import com.exercise.exercise.dto.productCategory.ProductCategoryRequest;
import com.exercise.exercise.model.product.Product;
import com.exercise.exercise.model.product.ProductCategory;
import com.exercise.exercise.repository.ProductCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MVCProductCategoryService {

    private static final Logger log = LoggerFactory.getLogger(MVCProductCategoryService.class);

    private final ProductCategoryRepository productCategoryRepository;

    @Autowired
    public MVCProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }
    // create
    public void save(ProductCategory productCategory) {
        log.info("saving product category");
        productCategoryRepository.save(productCategory);
    }
    // find all
    public List<ProductCategory> findAll() {
        log.info("finding all categories");
        return productCategoryRepository.findAll();
    }
    // find by id
    public ProductCategory findById(Long id) {
        log.info("finding by id");
        return productCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("category not found"));
    }
    // update
    public void update(Long productCategoryId, ProductCategoryRequest productCategoryData) {
        log.info("update product {}", productCategoryData);

        productCategoryRepository.findById(productCategoryId)
                .map(existingProductCategory -> updateEntity(productCategoryData, existingProductCategory))
                .map(updatedProductCategory -> productCategoryRepository.save(updatedProductCategory))
                .orElseThrow(() -> new RuntimeException("product category not found"));
    }

    private ProductCategory updateEntity(ProductCategoryRequest productCategoryData, ProductCategory existingProductCategory) {
        existingProductCategory.setName(productCategoryData.getName());
        existingProductCategory.setProducts((List<Product>) productCategoryData.getProduct());
        return existingProductCategory;
    }

    public void updateNew(ProductCategory productCategory) {
        log.info("update product category {}", productCategory);

        String name = productCategory.getName();
        productCategoryRepository.findByNameIgnoreCase(name)
                .filter(existingProductCategory -> existingProductCategory.getId().equals(productCategory.getId()))
                .map(existingProductCategory -> productCategoryRepository.save(productCategory))
                .orElseThrow(() -> {
                    log.error("product category with name {} already exists", name);
                    throw new ResourceAlreadyExistsException("product category with name " + name + " already exists");
                });
    }

    // delete
    @Transactional
    public void delete(Long id) {
        log.info("deleting by id");
        productCategoryRepository.deleteById(id);
    }

}
