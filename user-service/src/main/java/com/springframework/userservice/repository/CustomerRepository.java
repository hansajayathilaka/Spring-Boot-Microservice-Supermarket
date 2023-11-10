package com.springframework.userservice.repository;

import com.springframework.userservice.model.entity.CartProduct;
import com.springframework.userservice.model.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
}
