package com.ead.inventoryservice.service;

import com.ead.inventoryservice.dto.ProductRequest;
import com.ead.inventoryservice.dto.ProductResponse;
import com.ead.inventoryservice.model.Product;
import com.ead.inventoryservice.model.Stock;
import com.ead.inventoryservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/***
 * Provide business logic and act as a intermediary layer in controller and repository
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final List<Stock> stocks;

    public void createProduct(ProductRequest productRequest) {
        try {
            if (productRepository.existsByName(productRequest.getName())) {
                log.error("Product with name {} already exists", productRequest.getName());
                throw new RuntimeException("Product already exists");
            }
        } catch (Exception e) {
            log.error("Product with name {} already exists", productRequest.getName());
        }
        try {
            Product product = Product.builder()
                    .name(productRequest.getName())
                    .description(productRequest.getDescription())
                    .stocks(stocks)
                    .build();
            productRepository.save(product);
            log.info("Product {} is saved", product.getId());
        } catch (Exception e) {
            log.error("Error while saving the product to the database.");
        }
    }

    public List<ProductResponse> getAllProducts() {
        try {
            if (productRepository.findAll().isEmpty()) {
                log.error("No products found");
                throw new RuntimeException("No products found");
            }
        } catch (Exception e) {
            log.error("No products found");
        }
        try {
            List<Product> products = productRepository.findAll();
            return products.stream().map(this::mapToProductResponse).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error while fetching the products from the database.");
            return null;
        }
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .build();
    }

    public void deleteProduct(String id) {
        try {
            if (productRepository.existsById(id)) {
                productRepository.deleteById(id);
                log.info("Product with id {} is deleted", id);
            } else {
                throw new RuntimeException("Product not found");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void updateProduct(String id, ProductRequest productRequest) {
        try {
            Product productInDb = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
            Product product = Product.builder()
                    .id(productInDb.getId())
                    .name(productRequest.getName())
                    .description(productRequest.getDescription())
                    .stocks(productInDb.getStocks())
                    .build();
            productRepository.save(product);
            log.info("Product {} is updated", product.getId());
        } catch (Exception e) {
            log.error("Product with id {} is not found", id);
        }
    }
}
