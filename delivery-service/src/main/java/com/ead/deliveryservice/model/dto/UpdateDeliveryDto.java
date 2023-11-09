package com.ead.deliveryservice.model.dto;

import com.ead.deliveryservice.model.entry.Delivery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDeliveryDto {
    private String deliveryId;
    private Delivery.DeliveryStatus status;
}
