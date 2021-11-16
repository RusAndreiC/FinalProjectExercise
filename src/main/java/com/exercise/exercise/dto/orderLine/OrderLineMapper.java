package com.exercise.exercise.dto.orderLine;

import com.exercise.exercise.model.cartNorder.OrderLine;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Component
public class OrderLineMapper {
    public OrderLineResponse toDto(OrderLine orderLine) {
        OrderLineResponse dto = new OrderLineResponse();
        dto.setId(orderLine.getId());
        dto.setQuantity(orderLine.getQuantity());
        dto.setProduct(orderLine.getProduct());
        return dto;
    }

    public OrderLine toEntity(OrderLineRequest orderLineRequest) {
        OrderLine orderLine = new OrderLine();
        orderLine.setQuantity(orderLineRequest.getQuantity());
        orderLine.setProduct(orderLineRequest.getProduct());
        return orderLine;
    }

    public OrderLine toEntity(OrderLine orderLineToUpdate, OrderLineRequest updateRequest) {

        if (!StringUtils.isEmpty(updateRequest.getQuantity())) {
            orderLineToUpdate.setQuantity(updateRequest.getQuantity());
        }

        if (!StringUtils.isEmpty(updateRequest.getProduct())) {
            orderLineToUpdate.setProduct(updateRequest.getProduct());
        }


//        if (updateRequest.getPublished() != null && updateRequest.getPublished().isBefore(LocalDate.now())) {
//            productToUpdate.setPublished(updateRequest.getPublished());
//        }
        return orderLineToUpdate;
    }
}


