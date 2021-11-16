package com.exercise.exercise.controllers.MVCcontroller;

import com.exercise.exercise.dto.productCategory.ProductCategoryRequest;
import com.exercise.exercise.model.product.ProductCategory;
import com.exercise.exercise.service.MVCService.MVCProductCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

@Controller
public class ProductCategoryController {
    private static final Logger log = LoggerFactory.getLogger(ProductCategoryController.class);

    private final MVCProductCategoryService mvcProductCategoryService;

    @Autowired
    public ProductCategoryController(MVCProductCategoryService mvcProductCategoryService) {
        this.mvcProductCategoryService = mvcProductCategoryService;
    }


    @GetMapping("/admin/product-categories")
    public String showProductCategoriesPage(Model model) {
        // return a html page with products
        // add list of products
        List<ProductCategory> productCategories = mvcProductCategoryService.findAll();
        model.addAttribute("productCategoriesInView", productCategories);

        System.out.println("asd");
        log.info("Show category list");

        // resolved by the view resolver
        return "index-product-category";
    }

    @GetMapping("/admin/product-categories/add")
    public String showAddFrom(Model model) {
        ProductCategory newProductCategory = new ProductCategory();
        model.addAttribute("productCategory", newProductCategory);
//        return "nav-bar";
        return "product-category-add";
    }

    @PostMapping("/admin/product-categories/add")
    public String add(@ModelAttribute ProductCategory productCategory) {
        mvcProductCategoryService.save(productCategory);
        return "redirect:/admin/product-categories/";
    }

    @GetMapping("/admin/product-categories/{id}/edit")
    public String showEditForm(Model model,
                               @PathVariable Long id) {

        model.addAttribute("productCategory", mvcProductCategoryService.findById(id));
        return "product-category-edit";
    }

    public ModelAndView showEditForm2(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("productCate-edit");
        modelAndView.addObject("productCategory", mvcProductCategoryService.findById(id));
        return modelAndView;
    }

    @PostMapping("/admin/product-categories/{id}/edit")
    public String edit(
            @PathVariable Long id,
            @ModelAttribute ProductCategoryRequest productCategoryData) {

        mvcProductCategoryService.update(id, productCategoryData);
        return "redirect:/admin/product-categories/";
    }

    @GetMapping("/admin/product-categories/{id}/delete")
    public String delete(@PathVariable long id) {
        mvcProductCategoryService.delete(id);
        return "redirect:/admin/product-categories/";
    }
}
