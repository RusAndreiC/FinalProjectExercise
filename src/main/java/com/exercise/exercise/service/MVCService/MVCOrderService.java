package com.exercise.exercise.service.MVCService;

import com.exercise.exercise.dto.order.OrderRequest;
import com.exercise.exercise.exception.NotEnoughProductsInStockException;
import com.exercise.exercise.model.cartNorder.Orders;
import com.exercise.exercise.model.product.Product;
import com.exercise.exercise.repository.OrderRepository;
import com.exercise.exercise.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

@Service
public class MVCOrderService {

    private static final Logger log = LoggerFactory.getLogger(MVCOrderService.class);

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    private Map<Product, Integer> products = new HashMap<>();

    @Autowired
    public MVCOrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public void addProduct(Product product, int quantity) {
        if (products.containsKey(product)) {
            products.replace(product, products.get(product) + quantity);
        } else {
            products.put(product, quantity);
        }
    }

    public void removeProduct(Product product) {
        if (products.containsKey(product)) {
            if (products.get(product) > 1)
                products.replace(product, products.get(product) - 1);
            else if (products.get(product) == 1) {
                products.remove(product);
            }
        }
    }

    ;

   public Map<Product, Integer> getProductsInCart() {
        return Collections.unmodifiableMap(products);
    }


    //TODO creaza legatura user - order
    public void checkout() throws NotEnoughProductsInStockException {
        Product product;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Long productKey = entry.getKey().getId();
            // Refresh quantity for every product before checking
             product = productRepository.findById(productKey).orElseThrow();

            if (product.getQuantity() < entry.getValue())
                throw new NotEnoughProductsInStockException(product);

            entry.getKey().setQuantity(product.getQuantity() - entry.getValue());

            productRepository.save(entry.getKey());
        }

//        productRepository.save(products.keySet());
        productRepository.flush();
        products.clear();
    }

    public double getTotal() {
        return products.entrySet().stream()
                .map(entry -> BigDecimal.valueOf(entry.getKey().getPrice() * entry.getValue()))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO).doubleValue();

    }


    // create
    public void save(Orders order) {
        log.info("saving order {}", order.getId());
        orderRepository.save(order);
    }

    // find all
    public List<Orders> findAll() {
        log.info("finding all order");
        return orderRepository.findAll();
    }

    // find by id
    public Orders findById(Long id) {
        log.info("finding by id");
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    // update
    public void update(Long orderId, OrderRequest orderDTO) {
        log.info("update Order {}", orderDTO.getEntries().toString());

        orderRepository.findById(orderId)
                .map(existingOrder -> updateEntity(orderDTO, existingOrder))
                .map(updatedOrder -> orderRepository.save(updatedOrder))
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    private Orders updateEntity(OrderRequest orderData, Orders existingOrder) {
        existingOrder.setTotalPrice(orderData.getTotalPrice());
        existingOrder.setAddress(orderData.getAddress());
        existingOrder.setOrderDate(orderData.getOrderDate());
        existingOrder.setEntries(orderData.getEntries());
        existingOrder.setUser(orderData.getUser());
        existingOrder.setOrderStatus(orderData.getOrderStatus());
        return existingOrder;
    }

//    public void updateNew(Product product) {
//        log.info("update product {}", product);
//
//        String name = product.getName();
//        productRepository.findByNameIgnoreCase(name)
//                .filter(existingProduct -> existingProduct.getId().equals(product.getId()))
//                .map(existingProduct -> productRepository.save(product))
//                .orElseThrow(() -> {
//                    log.error("product with name {} already exists", name);
//                    throw new ResourceAlreadyExistsException("product with name " + name + " already exists");
//                });
//    }

    // delete
    @Transactional
    public void delete(Long id) {
        log.info("deleting by id");
        orderRepository.deleteById(id);
    }
}
