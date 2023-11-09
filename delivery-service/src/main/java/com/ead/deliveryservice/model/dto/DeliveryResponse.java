package com.ead.deliveryservice.model.dto;

import com.ead.deliveryservice.model.entry.Delivery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryResponse {
    private String id;
    private String refOrder;
    private String address;
    private Delivery.DeliveryStatus status;
    private String notes;
    private boolean isActive;

}
