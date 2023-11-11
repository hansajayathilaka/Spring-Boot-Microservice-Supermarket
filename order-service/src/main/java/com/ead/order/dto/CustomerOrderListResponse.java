package com.ead.order.dto;

import com.ead.order.model.OrderQuantity;
import com.ead.order.model.OrderStatus;
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
public class CustomerOrderListResponse {
    private String orderId;
    private LocalDateTime placedDate;
    private OrderStatus orderStatus;
    private Float total;
}
