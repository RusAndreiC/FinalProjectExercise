package com.exercise.exercise.service.RESTService;




import com.exercise.exercise.dto.order.OrderRequest;
import com.exercise.exercise.dto.order.OrderResponse;

import java.util.List;
import java.util.Map;

public interface OrderService {
    List<OrderResponse> findAll();

    OrderResponse findById(Long id);

    OrderResponse save(OrderRequest createOrderRequest);

    OrderResponse update(Long id, OrderRequest updateRequest);

    OrderResponse partialUpdate(Long id, Map<String, Object> updates);

    void delete(Long id);

    // find using filter
    List<OrderResponse> findByCategory(String categoryName);

}
