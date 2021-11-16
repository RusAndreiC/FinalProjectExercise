package com.exercise.exercise.controllers.RESTcontroller;


import com.exercise.exercise.dto.product.ProductRequest;
import com.exercise.exercise.dto.product.ProductResponse;
import com.exercise.exercise.service.RESTService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(RESTProductController.API_PRODUCTS)
public class RESTProductController {

    public static final String API_PRODUCTS = "/api/products";

    private final ProductService productService;

    @Autowired
    public RESTProductController(ProductService restProductService) {
        this.productService = restProductService;
    }

    @PostMapping("/")
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest productRequest) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(productService.save(productRequest));
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductResponse>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductResponse>> findByCategory(@PathVariable("category") String category) {
        return ResponseEntity.ok(productService.findByCategory(category));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(
        @PathVariable("id") Long id,
        @RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(productService.update(id, productRequest));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponse> partialUpdate(
        @PathVariable("id") Long id,
        @RequestBody Map<String, Object> productRequest) {
        return ResponseEntity.ok(productService.partialUpdate(id, productRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
