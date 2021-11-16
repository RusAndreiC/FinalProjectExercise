package com.exercise.exercise.repository;

import com.exercise.exercise.model.user.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

}
