package com.example.order.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;


@Document(value = "order")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Order {
    @Id
    private String orderId;
    private LocalDateTime placedDate;
    private LocalDateTime dispatchedDate;
    private OrderStatus orderStatus;
    private String customerId;
    private String storeKeeperId;
    private String deliveryPersonId;
    private List<OrderQuantity> orderQuantity;
}
