package com.GigaGrocery.product_service.dto;

import com.GigaGrocery.product_service.model.Stock;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private String id;
    private String name;
    private String category;
    private String description;
    private List<Stock> stocks;
}
