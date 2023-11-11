package com.ead.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * Reason for using two different classes for product request and response is that the attributes
 * are different in these two classes.
 *
 * Instead of ProductResponse class we can use Product class also, but as a good practice we
 * implement separate two classes for Product Request and Response.
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String name;
    private String description;
}
