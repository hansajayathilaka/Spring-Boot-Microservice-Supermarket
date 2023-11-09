package com.ead.deliveryservice.model.entry;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(value = "delivery")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Delivery {
    public enum DeliveryStatus {
        PENDING,  DELIVERED, CANCELLED
    }
    @Id
    private String id;
    private String refOrder;
    private String address;
    private DeliveryStatus status;
    private String notes;
    private boolean isActive;
}
