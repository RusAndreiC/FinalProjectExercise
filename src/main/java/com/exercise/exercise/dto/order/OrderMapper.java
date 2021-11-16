package com.exercise.exercise.dto.order;

import com.exercise.exercise.model.cartNorder.Orders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Component
public class OrderMapper {
    public OrderResponse toDto(Orders order) {
        OrderResponse dto = new OrderResponse();
        dto.setId(order.getId());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setAddress(order.getAddress());
        dto.setOrderDate(order.getOrderDate());
        dto.setEntries(order.getEntries());
        dto.setUser(order.getUser());
        dto.setOrderStatus(order.getOrderStatus());
        return dto;
    }

    public Orders toEntity(OrderRequest orderRequest) {
        Orders order = new Orders();
        order.setTotalPrice(orderRequest.getTotalPrice());
        order.setAddress(orderRequest.getAddress());
        order.setOrderDate(orderRequest.getOrderDate());
        order.setEntries(orderRequest.getEntries());
        order.setUser(orderRequest.getUser());
        order.setOrderStatus(orderRequest.getOrderStatus());
        return order;
    }

    public Orders toEntity(Orders orderToUpdate, OrderRequest updateRequest) {

        if (!StringUtils.isEmpty(updateRequest.getTotalPrice())) {
            orderToUpdate.setTotalPrice(updateRequest.getTotalPrice());
        }
        if (!StringUtils.isEmpty(updateRequest.getAddress())) {
            orderToUpdate.setAddress(updateRequest.getAddress());
        }
        if (!StringUtils.isEmpty(updateRequest.getOrderDate())) {
            orderToUpdate.setOrderDate(updateRequest.getOrderDate());
        }
        if (!StringUtils.isEmpty(updateRequest.getEntries())) {
            orderToUpdate.setEntries(updateRequest.getEntries());
        }
        if (!StringUtils.isEmpty(updateRequest.getUser())) {
            orderToUpdate.setUser(updateRequest.getUser());
        }
        if (!StringUtils.isEmpty(updateRequest.getOrderStatus())) {
            orderToUpdate.setOrderStatus(updateRequest.getOrderStatus());
        }



//        if (updateRequest.getPublished() != null && updateRequest.getPublished().isBefore(LocalDate.now())) {
//            productToUpdate.setPublished(updateRequest.getPublished());
//        }
        return orderToUpdate;
    }
}


