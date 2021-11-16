package com.exercise.exercise.controllers.RESTcontroller;


import com.exercise.exercise.dto.productCategory.ProductCategoryRequest;
import com.exercise.exercise.dto.productCategory.ProductCategoryResponse;
import com.exercise.exercise.service.RESTService.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(RESTProductCategoryController.API_CATEGORIES)
public class RESTProductCategoryController {
    public static final String API_CATEGORIES = "/api/categories";

    private final ProductCategoryService productCategoryService;

    @Autowired
    public RESTProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @PostMapping("/")
    public ResponseEntity<ProductCategoryResponse> create(@Valid @RequestBody ProductCategoryRequest productCategoryRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productCategoryService.save(productCategoryRequest));
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductCategoryResponse>> findAll() {
        return ResponseEntity.ok(productCategoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryResponse> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productCategoryService.findById(id));
    }

    @GetMapping("/{product}")
    public ResponseEntity<List<ProductCategoryResponse>> findByProduct(@PathVariable("product") String product) {
        return ResponseEntity.ok(productCategoryService.findByProduct(product));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductCategoryResponse> update(
            @PathVariable("id") Long id,
            @RequestBody ProductCategoryRequest productCategoryRequest) {
        return ResponseEntity.ok(productCategoryService.update(id, productCategoryRequest));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductCategoryResponse> partialUpdate(
            @PathVariable("id") Long id,
            @RequestBody Map<String, Object> productCategoryRequest) {
        return ResponseEntity.ok(productCategoryService.partialUpdate(id, productCategoryRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        productCategoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
