package com.example.order.dto;

import com.example.order.model.OrderQuantity;
import com.example.order.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderListResponse {
    private String orderId;
    private LocalDateTime placedDate;
    private LocalDateTime dispatchedDate;
    private OrderStatus orderStatus;
    private String customerId;
    private String storeKeeperId;
    private String deliveryPersonId;
    private List<OrderQuantity> orderQuantity;
}
