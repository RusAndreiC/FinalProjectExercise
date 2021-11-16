package com.exercise.exercise.repository;

import com.exercise.exercise.model.cartNorder.Orders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Orders, Long> {
    List<Orders> findAll();


}
