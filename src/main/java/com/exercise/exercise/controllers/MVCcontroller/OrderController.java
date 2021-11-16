package com.exercise.exercise.controllers.MVCcontroller;

import com.exercise.exercise.model.cartNorder.OrderLine;
import com.exercise.exercise.model.cartNorder.Orders;
import com.exercise.exercise.model.product.Product;
import com.exercise.exercise.service.MVCService.MVCOrderLineService;
import com.exercise.exercise.service.MVCService.MVCOrderService;
import com.exercise.exercise.service.MVCService.MVCProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);


    private final MVCOrderService mvcOrderService;
    private final MVCOrderLineService mvcOrderLineService;
    private final MVCProductService mvcProductService;

    public OrderController(MVCOrderService mvcOrderService, MVCOrderLineService mvcOrderLineService, MVCProductService mvcProductService) {
        this.mvcOrderService = mvcOrderService;
        this.mvcOrderLineService = mvcOrderLineService;
        this.mvcProductService = mvcProductService;
    }




    @PostMapping("/order/add/{productId}")
    public String add(@PathVariable("productId") long productId, Model model) {
        //1. Check if order exists, if not create one
        Orders order = (Orders) model.getAttribute("orderId");
        Product product = mvcProductService.findById(productId);
        orderBuilder(model, order);

        //Add product to order

        //2. Create orderLine from product;
        //trigger - butonu de pe pagina menu
        OrderLine orderLine = new OrderLine(1, product, order, product.getPrice());
        mvcOrderLineService.save(orderLine);

        //Check if there's multiple orderLines for the same product
        return "redirect:/menu/";
    }

    private void orderBuilder(Model model, Orders order) {
        if (order == null) {
            //Create order and add order to model
            Orders newOrder = new Orders();
            mvcOrderService.save(newOrder);
            model.addAttribute("orderId", newOrder);
        }
    }


}
