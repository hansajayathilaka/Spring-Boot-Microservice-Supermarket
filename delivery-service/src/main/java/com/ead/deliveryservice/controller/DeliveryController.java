package com.ead.deliveryservice.controller;

import com.ead.deliveryservice.model.dto.CreateDeliveryDto;
import com.ead.deliveryservice.model.dto.DeliveryResponseDto;
import com.ead.deliveryservice.model.dto.UpdateDeliveryDto;
import com.ead.deliveryservice.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/deliveries")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    // host/api/deliveries?orderId=82d45bcf-ebee-4f83-b6cb-4982b7b19717
    @GetMapping
    public ResponseEntity<DeliveryResponseDto> getDeliveryByOrderId(@RequestParam Optional<String> orderId){
        if (orderId.isPresent()) {
            return new ResponseEntity<>(deliveryService.getDeliveryByOrderId(orderId.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(deliveryService.getDelivery(), HttpStatus.OK);

    }

    // host/api/deliveries
    @PostMapping
    public ResponseEntity<Void> createDelivery(@RequestBody CreateDeliveryDto createDeliveryDto){
        deliveryService.create(createDeliveryDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // host/api/deliveries
    @PatchMapping
    public ResponseEntity<Void> updateDeliveryStatus(@RequestBody UpdateDeliveryDto updateDeliveryDto){
        deliveryService.updateDeliveryStatus(updateDeliveryDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
