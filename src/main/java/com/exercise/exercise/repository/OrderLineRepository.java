package com.exercise.exercise.repository;

import com.exercise.exercise.model.cartNorder.OrderLine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderLineRepository extends CrudRepository<OrderLine,Long> {
}
