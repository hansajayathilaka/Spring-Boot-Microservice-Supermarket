package com.ead.deliveryservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDeliveryDto {
    private String refOrder;
    private String address;
    private String notes;
}
