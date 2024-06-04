package com.ead.userservice.service.customer;

import com.ead.userservice.model.entity.CartProduct;
import com.ead.userservice.model.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer getCustomerById(String id);

    List<Customer> getAllCustomers();

    public Customer addCustomer(Customer customer);

    Customer updateCustomer(String customerId, Customer customer);

    Customer deleteCustomer(String customerId);

    // Cart Handling

    List<CartProduct> getCart(String customerId);

    CartProduct addToCart(String customerId, CartProduct product);

    List<CartProduct> updateCart(String customerId, List<CartProduct> cart);

    CartProduct deleteFromCart(String customerId, CartProduct product);

    void clearCart(String customerId);
}
