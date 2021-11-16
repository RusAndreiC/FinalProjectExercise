package com.exercise.exercise.controllers.MVCcontroller;


import com.exercise.exercise.dto.order.OrderProduct;
import com.exercise.exercise.dto.product.ProductRequest;
import com.exercise.exercise.model.product.Product;
import com.exercise.exercise.service.MVCService.MVCProductService;
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

// mvc controller
@Controller
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final MVCProductService mvcProductService;

    @Autowired
    public ProductController(MVCProductService mvcProductService) {
        this.mvcProductService = mvcProductService;
    }

    // http://localhost:8080/products

    // map url to controller method

    @GetMapping("/")
    public String showFirstProductsPage(Model model) {
        // return a html page with products
        // add list of products
        List<Product> products = mvcProductService.findAll();
        model.addAttribute("productsInView", products);
        model.addAttribute("orderProduct", new OrderProduct());

        // resolved by the view resolver
        return "menu";
    }

    @GetMapping("/menu/")
    public String showMenuPage(Model model) {
        // return a html page with products
        // add list of products
        List<Product> products = mvcProductService.findAll();
        model.addAttribute("productsInView", products);
//        model.addAttribute("orderProduct", new OrderProduct());

        // resolved by the view resolver
        return "menu";
    }

    @GetMapping("/admin/products")
    public String showProductsPage(Model model) {
        // return a html page with products
        // add list of products
        List<Product> products = mvcProductService.findAll();
        model.addAttribute("productsInView", products);

        // resolved by the view resolver
        return "index";
    }

    @GetMapping("/admin/products/add")
    public String showAddFrom(Model model) {
        Product newProduct = new Product();
        model.addAttribute("product", newProduct);
        return "product-add";
    }

    @PostMapping("/admin/products/add")
    public String add(@ModelAttribute Product product) {
        mvcProductService.save(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products/{id}/edit")
    public String showEditForm(Model model,
                               @PathVariable Long id) {

        model.addAttribute("product", mvcProductService.findById(id));
        return "product-edit";
    }

    public ModelAndView showEditForm2(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("product-edit");
        modelAndView.addObject("product", mvcProductService.findById(id));
        return modelAndView;
    }

    @PostMapping("/admin/products/{id}/edit")
    public String edit(
            @PathVariable Long id,
            @ModelAttribute ProductRequest productData) {

        mvcProductService.update(id, productData);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products/{id}/delete")
    public String delete(@PathVariable long id) {
        mvcProductService.delete(id);
        return "redirect:/admin/products";
    }
}
