package com.exercise.exercise.service.RESTService;

import com.exercise.exercise.dto.product.ProductMapper;
import com.exercise.exercise.dto.product.ProductRequest;
import com.exercise.exercise.dto.product.ProductResponse;
import com.exercise.exercise.exception.NotFoundException;
import com.exercise.exercise.exception.ProductNotFoundException;
import com.exercise.exercise.model.product.Product;
import com.exercise.exercise.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RESTProductService implements ProductService{

    private static final Logger log = LoggerFactory.getLogger(RESTProductService.class);
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ObjectMapper jacksonObjectMapper;
    
    @Autowired
    public RESTProductService(ProductRepository productRepository, ProductMapper productMapper, ObjectMapper jacksonObjectMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.jacksonObjectMapper = jacksonObjectMapper;
    }

    @Override
    public List<ProductResponse> findAll() {
        log.debug("finding all products");

        return productRepository.findAll().stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse findById(Long id) {
        log.debug("finding product by id: {}", id);

        return productRepository.findById(id)
                .map(productMapper::toDto)
                .orElseThrow(() -> new ProductNotFoundException("product not found"));
    }

    @Override
    public ProductResponse save(ProductRequest createProductRequest) {
        log.debug("saving product: {}", createProductRequest);

        boolean exists = productRepository.findByNameIgnoreCase(createProductRequest.getName()).isPresent();
        if (exists) {
            throw new NotFoundException("duplicate product");
        }

        Product product = productMapper.toEntity(createProductRequest);
        Product newProduct = productRepository.save(product);
        return productMapper.toDto(newProduct);
    }

    @Override
    public ProductResponse update(Long id, ProductRequest updateRequest) {
            log.debug("updating product with id: {} and request body: {}", id, updateRequest);

            return productRepository.findById(id)
                    .map(product -> productMapper.toEntity(product, updateRequest))
                    .map(productRepository::save)
                    .map(productMapper::toDto)
                    .orElseThrow(() -> new NotFoundException("product not found"));
        }

    @Override
    public ProductResponse partialUpdate(Long id, Map<String, Object> updates) {
        log.debug("patching product with id: {} and request body: {}", id, updates);
        // De-serialise request body into a DTO
        // Run some sort of validation
        // Load entity being updated
        // Copy fields that change over to the entity with the help of a Model Mapper
        // Save

        try {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("product not found"));

            // Jackson deserializes and copies value to the already initialised DTO
            jacksonObjectMapper.readerForUpdating(product)
                    .readValue(jacksonObjectMapper.writeValueAsBytes(updates));

            Product updatedProduct = productRepository.save(product);

            return productMapper.toDto(updatedProduct);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;    }

    @Override
    public void delete(Long id) {
        log.debug("deleting product with id: {}", id);

        productRepository.findById(id)
                .map(product -> {
                    productRepository.deleteById(id);
                    return product;
                })
                .orElseThrow(() -> new NotFoundException("product not found"));
    }

    @Override
    public List<ProductResponse> findByCategory(String categoryName) {
        log.debug("finding products by category name: {}", categoryName);

        return productRepository.findAll()
                .stream()
                .filter(product -> product.getCategory().getName().equals(categoryName))
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }
}
