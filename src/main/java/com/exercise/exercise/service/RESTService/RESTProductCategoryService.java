package com.exercise.exercise.service.RESTService;

import com.exercise.exercise.dto.productCategory.ProductCategoryMapper;
import com.exercise.exercise.dto.productCategory.ProductCategoryRequest;
import com.exercise.exercise.dto.productCategory.ProductCategoryResponse;
import com.exercise.exercise.exception.NotFoundException;
import com.exercise.exercise.exception.ProductNotFoundException;
import com.exercise.exercise.model.product.ProductCategory;
import com.exercise.exercise.repository.ProductCategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RESTProductCategoryService implements ProductCategoryService {

    private static final Logger log = LoggerFactory.getLogger(RESTProductCategoryService.class);
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductCategoryMapper productCategoryMapper;
    private final ObjectMapper jacksonObjectMapper;

    public RESTProductCategoryService(ProductCategoryRepository productCategoryRepository, ProductCategoryMapper productCategoryMapper, ObjectMapper jacksonObjectMapper) {
        this.productCategoryRepository = productCategoryRepository;
        this.productCategoryMapper = productCategoryMapper;
        this.jacksonObjectMapper = jacksonObjectMapper;
    }


    @Override
    public List<ProductCategoryResponse> findAll() {
        log.debug("finding all products categories");

        return productCategoryRepository.findAll().stream()
                .map(productCategoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductCategoryResponse findById(Long id) {
        log.debug("finding product category by id: {}", id);

        return productCategoryRepository.findById(id)
                .map(productCategoryMapper::toDto)
                .orElseThrow(() -> new ProductNotFoundException("product category not found"));
    }

    @Override
    public ProductCategoryResponse save(ProductCategoryRequest createProductCategoryRequest) {
        log.debug("saving product category: {}", createProductCategoryRequest);

        boolean exists = productCategoryRepository.findByNameIgnoreCase(createProductCategoryRequest.getName()).isPresent();
        if (exists) {
            throw new NotFoundException("duplicate product category");
        }

        ProductCategory productCategory = productCategoryMapper.toEntity(createProductCategoryRequest);
        ProductCategory newProductCategory = productCategoryRepository.save(productCategory);
        return productCategoryMapper.toDto(newProductCategory);
    }

    @Override
    public ProductCategoryResponse update(Long id, ProductCategoryRequest updateRequest) {
        log.debug("updating product category with id: {} and request body: {}", id, updateRequest);

        return productCategoryRepository.findById(id)
                .map(productCategory -> productCategoryMapper.toEntity(productCategory, updateRequest))
                .map(productCategoryRepository::save)
                .map(productCategoryMapper::toDto)
                .orElseThrow(() -> new NotFoundException("product category not found"));
    }

    @Override
    public ProductCategoryResponse partialUpdate(Long id, Map<String, Object> updates) {
        log.debug("patching product with id: {} and request body: {}", id, updates);

        try {
            ProductCategory productCategory = productCategoryRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("product category not found"));

            jacksonObjectMapper.readerForUpdating(productCategory)
                    .readValue(jacksonObjectMapper.writeValueAsBytes(updates));

            ProductCategory updatedProductCategory = productCategoryRepository.save(productCategory);

            return productCategoryMapper.toDto(updatedProductCategory);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        log.debug("deleting product category with id: {}", id);

        productCategoryRepository.findById(id)
                .map(productCategory -> {
                    productCategoryRepository.deleteById(id);
                    return productCategory;
                })
                .orElseThrow(() -> new NotFoundException("product category not found"));
    }

    @Override
    public List<ProductCategoryResponse> findByProduct(String productName) {
        return null;
    }


    //TODO avem nevoie de ajutor in crearea functiei de findByProduct
//    @Override
//    public List<ProductCategoryResponse> findByProduct(String productName) {
//        log.debug("finding products by product name: {}", productName);
//
//        return productCategoryRepository.findAll()
//                .stream()
//                .filter(productCategory -> productCategory.getProducts().(product -> product.getName().equals(productName)))
//
////                        .stream()
////                        .filter(product -> product.getName().equals(productName)))
//
//                .map(productCategoryMapper::toDto)
//                .collect(Collectors.toList());
//    }
}

