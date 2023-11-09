package com.ead.deliveryservice.repository;

import com.ead.deliveryservice.model.entry.Delivery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryRepository extends MongoRepository<Delivery, String> {
    Optional<Delivery> findByRefOrder(String orderId);
}
