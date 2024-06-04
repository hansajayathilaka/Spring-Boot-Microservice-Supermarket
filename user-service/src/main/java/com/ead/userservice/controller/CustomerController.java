package com.ead.userservice.controller;

import com.ead.userservice.model.entity.CartProduct;
import com.ead.userservice.model.entity.Customer;
import com.ead.userservice.service.customer.CustomerService;
import com.ead.userservice.service.customer.CustomerServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public Customer getCustomer(@PathVariable String customerId) {
        return customerService.getCustomerById(customerId);
    }

    @GetMapping
    public Iterable<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping
    public Customer addCustomer(@RequestBody Customer user) {
        return customerService.addCustomer(user);
    }

    @PutMapping("/{customerId}")
    public Customer updateCustomer(@PathVariable String customerId, @RequestBody Customer customer) {
        return customerService.updateCustomer(customerId, customer);
    }

    @DeleteMapping("/{customerId}")
    public Customer deleteCustomer(@PathVariable String customerId) {
        return customerService.deleteCustomer(customerId);
    }

    // Cart Endpoints

    @GetMapping("/{customerId}/cart")
    public List<CartProduct> getCart(@PathVariable String customerId) {
        return customerService.getCart(customerId);
    }

    @PostMapping("/{customerId}/cart")
    public CartProduct addToCart(@PathVariable String customerId, @RequestBody CartProduct product) {
        return customerService.addToCart(customerId, product);
    }

    @PutMapping("/{customerId}/cart")
    public List<CartProduct> updateCart(@PathVariable String customerId, @RequestBody List<CartProduct> cart) {
        return customerService.updateCart(customerId, cart);
    }

    @DeleteMapping("/{customerId}/cart")
    public CartProduct deleteFromCart(@PathVariable String customerId, @RequestBody CartProduct product) {
        return customerService.deleteFromCart(customerId, product);
    }

    @DeleteMapping("/{customerId}/clear-cart")
    public ResponseEntity<String> clearCart(@PathVariable String customerId) {
        customerService.clearCart(customerId);

        return ResponseEntity.ok().body("Cart cleared successfully");
    }
}
