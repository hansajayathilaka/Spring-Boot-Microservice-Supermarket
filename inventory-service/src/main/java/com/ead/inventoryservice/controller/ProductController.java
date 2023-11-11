package com.ead.inventoryservice.controller;

import com.ead.inventoryservice.dto.ProductRequest;
import com.ead.inventoryservice.dto.ProductResponse;
import com.ead.inventoryservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 * Contains the restapi controllers for handling HTTP requests and responses
 */
@Slf4j
@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * Create a new product
     * @param productRequest
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }

    /**
     * Get all the products
     * @return
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    /**
     * Get a specific product
     * @param id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }

    /**
     * Update a specific product
     * @param id
     * @param productRequest
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@PathVariable String id, @RequestBody ProductRequest productRequest) {
        productService.updateProduct(id, productRequest);
    }

}
