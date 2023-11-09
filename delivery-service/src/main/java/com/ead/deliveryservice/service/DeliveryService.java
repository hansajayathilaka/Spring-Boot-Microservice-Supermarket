package com.ead.deliveryservice.service;

import com.ead.deliveryservice.model.dto.CreateDeliveryDto;
import com.ead.deliveryservice.model.dto.DeliveryResponseDto;
import com.ead.deliveryservice.model.dto.UpdateDeliveryDto;

public interface DeliveryService {
    void create(CreateDeliveryDto createDeliveryDto);
    DeliveryResponseDto getDelivery();
    DeliveryResponseDto getDeliveryByOrderId(String orderId);
    void updateDeliveryStatus(UpdateDeliveryDto updateDeliveryDto);
}
