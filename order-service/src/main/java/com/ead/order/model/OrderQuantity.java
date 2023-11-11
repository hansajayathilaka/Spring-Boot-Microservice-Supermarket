package com.example.order.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "orderQuantity")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class OrderQuantity {
    private String stockId;
    private Integer quantity;

}
