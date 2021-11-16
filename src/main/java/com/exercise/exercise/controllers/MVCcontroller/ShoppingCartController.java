package com.exercise.exercise.controllers.MVCcontroller;


import com.exercise.exercise.exception.NotEnoughProductsInStockException;
import com.exercise.exercise.model.product.Product;
import com.exercise.exercise.service.MVCService.MVCOrderService;
import com.exercise.exercise.service.MVCService.MVCProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ShoppingCartController {

    private final MVCOrderService mVCOrderService;

    private final MVCProductService productService;

    @Autowired
    public ShoppingCartController(MVCOrderService mVCOrderService, MVCProductService productService) {
        this.mVCOrderService = mVCOrderService;
        this.productService = productService;
    }

    @GetMapping("/shoppingCart")
    public ModelAndView shoppingCart() {
        ModelAndView modelAndView = new ModelAndView("/shoppingCart");
        modelAndView.addObject("products", mVCOrderService.getProductsInCart());
        modelAndView.addObject("total", mVCOrderService.getTotal());
        return modelAndView;
    }

    @GetMapping("/shoppingCart/addProduct/{productId}")
    public ModelAndView addProductToCart(@PathVariable("productId") Long productId, @RequestParam("productQuantity") int quantity) {
        Product product = productService.findById(productId);
        if (product != null) {
            mVCOrderService.addProduct(product, quantity);
        }
        return shoppingCart();
    }

    @GetMapping("/shoppingCart/removeProduct/{productId}")
    public ModelAndView removeProductFromCart(@PathVariable("productId") Long productId) {
        Product product = productService.findById(productId);
        if (product != null) {
            mVCOrderService.removeProduct(product);
        }
        return shoppingCart();
    }

    @GetMapping("/shoppingCart/checkout")
    public ModelAndView checkout() {
        try {
            mVCOrderService.checkout();
        } catch (NotEnoughProductsInStockException e) {
            return shoppingCart().addObject("outOfStockMessage", e.getMessage());
        }
        return shoppingCart();
    }
}
